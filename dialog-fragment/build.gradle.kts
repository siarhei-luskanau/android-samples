buildscript {

    //    ext {
//        //Android
//        compileSdkVersion = 28
//        targetSdkVersion = 28
//        minSdkVersion = 16
//        buildToolsVersion = "28.0.3"
//
//        //Libraries
//        kotlinVersion = '1.3.21'
//        materialVersion = "1.1.0-alpha03"
//        timberVersion = '4.7.1'
//
//        appDependencies = [
//                kotlinStdlib: "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion",
//        ]
//    }

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
