plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "siarhei.luskanau.example.workmanager"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.workmanager"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.timber)
    implementation(Libraries.material)
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)

    implementation("androidx.work:work-runtime-ktx:2.7.1")
}
