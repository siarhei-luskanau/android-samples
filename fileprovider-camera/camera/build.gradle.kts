plugins {
    androidLibraryConvention
}

android {
    namespace = "com.example.camera.library"
}

dependencies {
    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.androidxCore)
}