@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "androidx.navigation") {
                useModule("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "newsapp"
include(":androidApp")
include(":shared")
include(":androidCore")
