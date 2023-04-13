plugins {
    androidApplicationConvention
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "siarhei.luskanau.example.navigation"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.navigation"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(Libraries.material)
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)
}
