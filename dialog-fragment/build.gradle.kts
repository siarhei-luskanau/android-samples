buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0")
        classpath(kotlin("gradle-plugin", version = "1.3.50"))
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.0.1")
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    apply("$rootDir/ktlint.gradle")
    apply("$rootDir/detekt.gradle")

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
