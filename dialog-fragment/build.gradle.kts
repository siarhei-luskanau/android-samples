plugins {
    id("androidApplicationConvention")
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
    implementation(libs.android.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
}
