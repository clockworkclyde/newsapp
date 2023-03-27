plugins {
   id("com.android.library")
   id("org.jetbrains.kotlin.android")
   id("kotlin-kapt")
}

android {
   namespace = "com.github.clockworkclyde.androidcoredesign"
   compileSdk = 33

   defaultConfig {
      minSdk = 24
      targetSdk = 33

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      consumerProguardFiles("consumer-rules.pro")
   }
   buildFeatures {
      dataBinding = true
   }
   buildTypes {
      release {
         isMinifyEnabled = false
         proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
         )
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

   implementation(AndroidDependencies.kotlinCoreKtx)
   implementation(AndroidDependencies.appCompat)
   implementation(AndroidDependencies.material)
   implementation(AndroidDependencies.adapterDelegates)
   implementation(AndroidDependencies.adapterDelegatesViewBinding)
   implementation(AndroidDependencies.timber)
   implementation(AndroidDependencies.glide)

   testImplementation("junit:junit:4.13.2")
   androidTestImplementation("androidx.test.ext:junit:1.1.5")
   androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}