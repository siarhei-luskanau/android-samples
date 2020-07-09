println("gradle.startParameter.taskNames: ${gradle.startParameter.taskNames}")

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(GradlePlugin.androidToolsBuildGradle)
        classpath(GradlePlugin.kotlinGradlePlugin)
        classpath(GradlePlugin.navigationSafeArgsGradlePlugin)
        classpath(GradlePlugin.androidJunit5Plugin)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.9.0")
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }

    apply(from = "$rootDir/ktlint.gradle.kts")
    apply(from = "$rootDir/detekt.gradle")

    plugins.configureEach {
        (this as? com.android.build.gradle.internal.plugins.BasePlugin<*, *>)?.extension?.apply {
            compileSdkVersion(BuildVersions.compileSdkVersion)
            buildToolsVersion = BuildVersions.buildToolsVersion

            defaultConfig {
                minSdkVersion(BuildVersions.minSdkVersion)
                targetSdkVersion(BuildVersions.targetSdkVersion)
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            testOptions {
                animationsDisabled = true
                unitTests(delegateClosureOf<com.android.build.gradle.internal.dsl.TestOptions.UnitTestOptions> {
                    //isReturnDefaultValues = true
                    all { test: Test ->
                        test.testLogging.events = setOf(
                                org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                                org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
                        )
                    }
                })
            }

            buildFeatures.viewBinding = true
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions { jvmTarget = JavaVersion.VERSION_1_8.toString() }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
