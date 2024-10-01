plugins {
    id("androidLibraryConvention")
}

android {
    namespace = "com.example.camera.library"
}

dependencies {
    implementation(libs.androidx.core)
}
