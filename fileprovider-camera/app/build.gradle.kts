plugins {
    id("androidApplicationConvention")
}

android {
    namespace = "siarhei.luskanau.example.camera.app"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.camera.app"
        versionCode = 1
        versionName = "1.0"
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
    implementation(libs.android.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.timber)
}
