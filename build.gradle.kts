println("gradle.startParameter.taskNames: ${gradle.startParameter.taskNames}")
System.getProperties().forEach { key, value -> println("System.getProperties(): $key=$value") }
System.getenv().forEach { (key, value) -> println("System.getenv(): $key=$value") }

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(GradlePlugin.androidToolsBuildGradle)
        classpath(GradlePlugin.kotlinGradlePlugin)
        classpath(GradlePlugin.navigationSafeArgsGradlePlugin)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version(PublicVersions.detekt)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    apply(from = "$rootDir/ktlint.gradle.kts")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    plugins.configureEach {
        (this as? com.android.build.gradle.internal.plugins.BasePlugin<*, *, *, *, *, *, *, *>)?.extension?.apply {
            compileSdkVersion(BuildVersions.compileSdkVersion)
            buildToolsVersion = BuildVersions.buildToolsVersion

            defaultConfig {
                minSdk = BuildVersions.minSdkVersion
                targetSdk = BuildVersions.targetSdkVersion
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            compileOptions.isCoreLibraryDesugaringEnabled = true
            buildFeatures.viewBinding = true

            testOptions {
                animationsDisabled = true
                unitTests(delegateClosureOf<com.android.build.gradle.internal.dsl.TestOptions.UnitTestOptions> {
                    all { test: Test ->
                        test.testLogging.events = setOf(
                            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                        )
                    }
                })
            }

            dependencies {
                "coreLibraryDesugaring"(Libraries.desugarJdkLibs)
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
