private object Versions {
    const val androidToolsBuildGradle = "4.1.0-beta03"
    const val desugar = "1.0.9"
    const val kotlin = "1.3.72"
    const val kotlinxCoroutines = "1.3.7"
    const val navigation = "2.3.0"
    const val timber = "4.7.1"
    const val material = "1.3.0-alpha01"
    const val activity = "1.2.0-alpha06"
    const val fragment = "1.3.0-alpha06"
    const val androidxCore = "1.5.0-alpha01"
    const val constraintlayout = "2.0.0-beta7"

    const val androidJunit5Gradle = "1.6.2.0"
    const val googleServicesGradle = "4.3.3"
}

object BuildVersions {
    const val platformVersion = 30
    const val compileSdkVersion = platformVersion
    const val targetSdkVersion = 30
    const val buildToolsVersion = "30.0.0"
    const val minSdkVersion = 21
}

object Libraries {
    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"
    const val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val androidxCore = "androidx.core:core:${Versions.androidxCore}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object GradlePlugin {
    const val androidToolsBuildGradle = "com.android.tools.build:gradle:${Versions.androidToolsBuildGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val androidJunit5Plugin = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.androidJunit5Gradle}"
    const val googleServicePlugin = "com.google.gms:google-services:${Versions.googleServicesGradle}"
}