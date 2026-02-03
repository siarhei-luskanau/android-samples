pluginManagement {
    includeBuild("convention-plugin-android-library")
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
    }
}

include(
    ":dialog-fragment",
    ":fileprovider-camera:app",
    ":fileprovider-camera:camera",
    ":fonts",
    ":navigation:app",
    ":navigation:composeApp",
    ":workmanager:app",
    ":workmanager:composeApp"
)
