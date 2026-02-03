plugins {
    id("androidApplicationConvention")
}

android {
    namespace = "siarhei.luskanau.example.workmanager.app"

    defaultConfig {
        applicationId = "siarhei.luskanau.example.workmanager"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(":workmanager:composeApp"))
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.timber)
}
