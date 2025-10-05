plugins {
    id("multiplatformConvention")
    alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
    namespace = "siarhei.luskanau.example.workmanager"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.workmanager"
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures.viewBinding = true
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.android.material)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.fragment.ktx)
            implementation(libs.androidx.navigation.fragment.ktx)
            implementation(libs.androidx.navigation.ui.ktx)
            implementation(libs.androidx.work.runtime.ktx)
            implementation(libs.timber)
        }
    }
}
