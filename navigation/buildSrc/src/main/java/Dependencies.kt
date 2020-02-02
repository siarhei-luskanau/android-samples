private object Versions {
    const val androidToolsBuildGradle = "3.6.0-rc02"
    const val kotlin = "1.3.61"
    const val material = "1.2.0-alpha04"
    const val navigation = "2.2.0-rc02"
    const val constraintLayout = "2.0.0-beta3"
}

object BuildVersions {
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val buildToolsVersion = "29.0.2"
    const val minSdkVersion = 21
}

object Libraries {
    const val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object GradlePlugin {
    const val androidToolsBuildGradle =
        "com.android.tools.build:gradle:${Versions.androidToolsBuildGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}