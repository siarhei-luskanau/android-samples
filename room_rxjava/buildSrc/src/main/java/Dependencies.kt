private object Versions {
    const val androidToolsBuildGradle = "3.6.0-rc02"
    const val kotlin = "1.3.61"
    const val timber = "4.7.1"
    const val material = "1.2.0-alpha04"
}

object BuildVersions {
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val buildToolsVersion = "29.0.2"
    const val minSdkVersion = 21
}

object Libraries {
    const val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object GradlePlugin {
    const val androidToolsBuildGradle =
        "com.android.tools.build:gradle:${Versions.androidToolsBuildGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}