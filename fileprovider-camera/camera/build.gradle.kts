import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("de.mannodermaus.android-junit5")
}

android.testOptions.junitPlatform.jacocoOptions.taskGenerationEnabled = false

dependencies {
    coreLibraryDesugaring(Libraries.desugarJdkLibs)

    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.androidxCore)
}