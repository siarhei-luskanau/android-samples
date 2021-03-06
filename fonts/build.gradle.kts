plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = "siarhei.luskanau.example.fonts"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    coreLibraryDesugaring(Libraries.desugarJdkLibs)

    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.material)
}