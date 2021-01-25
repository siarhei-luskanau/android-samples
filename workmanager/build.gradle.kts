plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    defaultConfig {
        applicationId = "siarhei.luskanau.example.workmanager"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    coreLibraryDesugaring(Libraries.desugarJdkLibs)

    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.timber)
    implementation(Libraries.material)
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)

    implementation("androidx.work:work-runtime-ktx:2.5.0-rc01")
}
