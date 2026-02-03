println("gradle.startParameter.taskNames: ${gradle.startParameter.taskNames}")
System.getProperties().forEach { (key, value) -> println("System.getProperties(): $key=$value") }
System.getenv().forEach { (key, value) -> println("System.getenv(): $key=$value") }

buildscript {
    dependencies {
        classpath(libs.android.tools.gradle)
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.kmp.library).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.detekt)
    alias(libs.plugins.jetbrains.compose).apply(false)
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.navigation.safeargs.kotlin).apply(false)
}

allprojects {
    apply(from = "$rootDir/ktlint.gradle")
    apply(plugin = "io.gitlab.arturbosch.detekt")
}
