println("gradle.startParameter.taskNames: ${gradle.startParameter.taskNames}")
System.getProperties().forEach { key, value -> println("System.getProperties(): $key=$value") }
System.getenv().forEach { (key, value) -> println("System.getenv(): $key=$value") }

buildscript {
    dependencies {
        classpath(libs.android.tools.gradle)
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.navigation.safeargs.kotlin).apply(false)
}

allprojects {
    apply(from = "$rootDir/ktlint.gradle")
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
