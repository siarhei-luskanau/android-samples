plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(BuildVersions.compileSdkVersion)
    buildToolsVersion = BuildVersions.buildToolsVersion

    defaultConfig {
        applicationId = "siarhei.luskanau.example.camera.appcrop"
        minSdkVersion(BuildVersions.minSdkVersion)
        targetSdkVersion(BuildVersions.targetSdkVersion)
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

    flavorDimensions("default")
    productFlavors {
        create("flavor1") {
            setDimension("default")
            applicationIdSuffix = ".flavor1"
            versionNameSuffix = "-flavor1"
        }
        create("flavor2") {
            setDimension("default")
            applicationIdSuffix = ".flavor2"
            versionNameSuffix = "-flavor2"
        }
    }

    viewBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Libraries.kotlinStdlibJdk8)
    implementation(Libraries.timber)
    implementation(Libraries.material)
    implementation(Libraries.constraintLayout)
    implementation("com.mikhaellopez:circularimageview:4.1.1")
    implementation("com.github.miguelbcr:RxPaparazzo:0.6.1-2.x")
    implementation("io.reactivex.rxjava2:rxandroid:+")
    implementation("io.reactivex.rxjava2:rxkotlin:+")
}
