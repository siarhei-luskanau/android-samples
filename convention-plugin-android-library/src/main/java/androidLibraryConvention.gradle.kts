val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = libs.findVersion("android-build-compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = libs.findVersion("android-build-minSdk").get().requiredVersion.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility =
            JavaVersion.valueOf(
                libs.findVersion("build-javaVersion").get().requiredVersion
            )
        targetCompatibility =
            JavaVersion.valueOf(
                libs.findVersion("build-javaVersion").get().requiredVersion
            )
    }

    testOptions {
        animationsDisabled = true
        unitTests {
            isIncludeAndroidResources = true
            all { test ->
                test.testLogging {
                    events = org.gradle.api.tasks.testing.logging.TestLogEvent.values().toSet()
                    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                }
            }
        }
    }
}
