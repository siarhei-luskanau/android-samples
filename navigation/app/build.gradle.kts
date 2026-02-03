plugins {
    id("androidApplicationConvention")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "siarhei.luskanau.example.navigation.app"

    defaultConfig {
        applicationId = "siarhei.luskanau.example.navigation"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":navigation:composeApp"))
    implementation(libs.androidx.activity.compose)
}
