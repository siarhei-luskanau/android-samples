import java.util.Properties

private object Versions {
    private val versionsProperties = Properties().apply {
        Versions::class.java.classLoader.getResourceAsStream("build_src_versions.properties")
            .use { load(it) }
    }

    val androidToolsBuildGradle: String =
        versionsProperties["version.androidToolsBuildGradle"].toString()
    val kotlin: String = versionsProperties["version.kotlin"].toString()
    const val desugar = "2.0.0"
    const val kotlinxCoroutines = "1.6.4"
    const val navigation = "2.5.3"
    const val timber = "5.0.1"
    const val material = "1.7.0"
    const val activity = "1.6.1"
    const val fragment = "1.5.5"
    const val androidxCore = "1.9.0"
}

object PublicVersions {
    val kotlin = Versions.kotlin
    const val ktlint = "0.48.1"
    const val detekt = "1.22.0"
}

object BuildVersions {
    const val platformVersion = 33
    const val compileSdkVersion = platformVersion
    const val targetSdkVersion = 33
    const val buildToolsVersion = "33.0.1"
    const val minSdkVersion = 21
}

object Libraries {
    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugar}"
    val kotlinStdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
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
    val androidToolsBuildGradle = "com.android.tools.build:gradle:${Versions.androidToolsBuildGradle}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgsGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}