plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "siarhei.luskanau.example.fonts"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.fonts"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.material)
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
}