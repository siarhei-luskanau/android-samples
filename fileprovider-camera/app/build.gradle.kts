plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "siarhei.luskanau.example.camera.app"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.camera.app"
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions.add("default")
    productFlavors {
        create("flavor1") {
            dimension = "default"
            applicationIdSuffix = ".flavor1"
            versionNameSuffix = "-flavor1"
        }
        create("flavor2") {
            dimension = "default"
            applicationIdSuffix = ".flavor2"
            versionNameSuffix = "-flavor2"
        }
    }
}

dependencies {
    implementation(project(":fileprovider-camera:camera"))

    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.timber)
    implementation(Libraries.material)
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
}
