plugins {
    id("androidApplicationConvention")
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
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment.ktx)
}
