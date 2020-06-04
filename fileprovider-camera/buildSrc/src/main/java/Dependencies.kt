private object Versions {
    const val androidToolsBuildGradle = "4.0.0"
    const val kotlin = "1.3.72"
    const val timber = "4.7.1"
    const val material = "1.3.0-alpha01"
    const val androidxCoreVersion = "1.4.0-alpha01"
    const val constraintlayoutVersion = "2.0.0-beta6"
}

object BuildVersions {
    const val platformVersion = "R"
    const val compileSdkVersion = "android-$platformVersion"
    const val targetSdkVersion = 30
    const val buildToolsVersion = "30.0.0-rc4"
    const val minSdkVersion = 21
}

object Libraries {
    const val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidxCore = "androidx.core:core:${Versions.androidxCoreVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayoutVersion}"
}

object GradlePlugin {
    const val androidToolsBuildGradle =
        "com.android.tools.build:gradle:${Versions.androidToolsBuildGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}