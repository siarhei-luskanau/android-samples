buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0")
        classpath(kotlin("gradle-plugin", version = "1.3.31"))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    apply("$rootDir/ktlint.gradle")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
