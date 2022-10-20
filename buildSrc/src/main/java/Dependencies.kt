private object Versions {
    const val androidToolsBuildGradle = "7.3.1"
    const val desugar = "1.2.2"
    const val kotlin = "1.7.20"
    const val kotlinxCoroutines = "1.6.4"
    const val navigation = "2.5.2"
    const val timber = "4.7.1"
    const val material = "1.7.0"
    const val activity = "1.6.0"
    const val fragment = "1.5.3"
    const val androidxCore = "1.9.0"
}

object PublicVersions {
    const val kotlin = Versions.kotlin
    const val ktlint = "0.47.1"
    const val detekt = "1.21.0"
}

object BuildVersions {
    const val platformVersion = 33
    const val compileSdkVersion = platformVersion
    const val targetSdkVersion = 33
    const val buildToolsVersion = "33.0.0"
    const val minSdkVersion = 21
}

object Libraries {
    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"
    const val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val androidxCore = "androidx.core:core:${Versions.androidxCore}"
}

object GradlePlugin {
    const val androidToolsBuildGradle = "com.android.tools.build:gradle:${Versions.androidToolsBuildGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}