plugins {
    id("multiplatformConvention")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidLibrary.namespace = "siarhei.luskanau.example.navigation"

    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.lifecycle.viewmodel.compose)
            implementation(libs.jetbrains.navigation.compose)
            implementation(libs.koin.compose)
            implementation(libs.kotlinx.serialization.json)
            implementation(project.dependencies.platform(libs.koin.bom))
        }
    }
}
