@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.github.clockworkclyde.newsapp.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.github.clockworkclyde.newsapp.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        dataBinding = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":androidCore"))
    implementation(project(":androidCoreDesign"))

    // Logger
    implementation(AndroidDependencies.timber)

    // Common
    implementation(AndroidDependencies.kotlinCoreKtx)
    implementation(AndroidDependencies.material)
    implementation(AndroidDependencies.appCompat)
    implementation(AndroidDependencies.constraintLayout)

    // RV
    implementation(AndroidDependencies.fastAdapter)

    // Image loading
    implementation(AndroidDependencies.glide)
    implementation(AndroidDependencies.glideKapt)

    // Ktx
    implementation(AndroidDependencies.lifecycleKtx)
    implementation(AndroidDependencies.navigationUiKtx)
    implementation(AndroidDependencies.navigationUiFragmentKtx)
    implementation(AndroidDependencies.viewModelKtx)
}