plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.1"

    defaultConfig {
        applicationId = "siarhei.luskanau.example.dialogfragment"
        minSdkVersion(16)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }

    dataBinding{
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk8", version = "1.3.41"))
    implementation("com.google.android.material:material:1.1.0-alpha08")
}