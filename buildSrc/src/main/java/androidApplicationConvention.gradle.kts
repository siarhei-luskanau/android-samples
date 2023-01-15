import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.delegateClosureOf
import org.gradle.kotlin.dsl.dependencies
import com.android.build.gradle.internal.dsl.TestOptions
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = BuildVersions.compileSdkVersion
    buildToolsVersion = BuildVersions.buildToolsVersion

    defaultConfig {
        minSdk = BuildVersions.minSdkVersion
        targetSdk = BuildVersions.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions.isCoreLibraryDesugaringEnabled = true
    buildFeatures.viewBinding = true

    dependencies {
        "coreLibraryDesugaring"(Libraries.desugarJdkLibs)
    }

    testOptions {
        animationsDisabled = true
        unitTests {
            isIncludeAndroidResources = true
            all { test ->
                test.testLogging {
                    events = setOf(
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED,
                        TestLogEvent.FAILED,
                        TestLogEvent.STANDARD_OUT,
                        TestLogEvent.STANDARD_ERROR
                    )
                    exceptionFormat = TestExceptionFormat.FULL
                }
            }
        }
    }
}
