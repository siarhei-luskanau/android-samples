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
    implementation(libs.android.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}
