plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    defaultConfig {
        applicationId = "siarhei.luskanau.example.navigation"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.material)
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)
}