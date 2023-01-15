println("gradle.startParameter.taskNames: ${gradle.startParameter.taskNames}")
System.getProperties().forEach { key, value -> println("System.getProperties(): $key=$value") }
System.getenv().forEach { (key, value) -> println("System.getenv(): $key=$value") }

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(GradlePlugin.androidToolsBuildGradle)
        classpath(GradlePlugin.kotlinGradlePlugin)
        classpath(GradlePlugin.navigationSafeArgsGradlePlugin)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version(PublicVersions.detekt)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    apply(from = "$rootDir/ktlint.gradle.kts")
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
