plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "siarhei.luskanau.example.camera.appcrop"
    defaultConfig {
        applicationId = "siarhei.luskanau.example.camera.appcrop"
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
    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.timber)
    implementation(Libraries.material)
    implementation(Libraries.activityKtx)
    implementation(Libraries.fragmentKtx)
    implementation("com.mikhaellopez:circularimageview:4.3.1")
    implementation("com.github.miguelbcr:RxPaparazzo:0.6.1-2.x")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
}
