plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(28)
    buildToolsVersion = "28.0.3"

    defaultConfig {
        applicationId = "siarhei.luskanau.example.dialogfragment"
        minSdkVersion(16)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
    }

    dataBinding{
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk8", version = "1.3.21"))
    implementation("com.google.android.material:material:1.1.0-alpha05")
}