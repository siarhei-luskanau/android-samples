pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

include(
    ":dialog-fragment",
    ":fileprovider-camera:app",
    ":fileprovider-camera:camera",
    ":fonts",
    ":navigation",
    ":workmanager"
)
