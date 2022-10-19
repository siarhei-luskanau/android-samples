plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.example.camera.library"
}

dependencies {
    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.androidxCore)
}