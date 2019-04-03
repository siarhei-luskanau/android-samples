buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.1")
        classpath(kotlin("gradle-plugin", version = "1.3.21"))
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
