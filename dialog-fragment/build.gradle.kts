plugins {
    androidApplicationConvention
}

android {
    namespace = "siarhei.luskanau.example.dialogfragment"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.dialogfragment"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(Libraries.material)
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
}
