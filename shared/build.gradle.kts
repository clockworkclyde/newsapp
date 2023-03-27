import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
   kotlin("multiplatform")
   id("com.android.library")
   kotlin("plugin.serialization")
}

kotlin {
   android {
      compilations.all {
         kotlinOptions {
            jvmTarget = "1.8"
         }
      }
   }

   listOf(
      iosX64(),
      iosArm64(),
      iosSimulatorArm64()
   ).forEach {
      it.binaries.framework {
         baseName = "shared"
      }
   }

   val ktorVersion = "1.5.0"
   val coroutinesVersion = "1.4.2-native-mt"
   val serializationVersion = "1.0.1"

   sourceSets {
      val commonMain by getting {
         dependencies {
            api("org.jetbrains.kotlinx:kotlinx-serialization-core:${serializationVersion}")

            // Ktor client + okhttp
            implementation("io.ktor:ktor-client-core:${ktorVersion}")
            implementation("io.ktor:ktor-client-json:${ktorVersion}")
            implementation("io.ktor:ktor-client-serialization:${ktorVersion}")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")

            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")

            // Kodein DI
            implementation("org.kodein.di:kodein-di:7.1.0")
         }
      }
      val commonTest by getting {
         dependencies {
            implementation(kotlin("test"))
         }
      }
      val androidMain by getting {
         dependencies {
            //implementation("com.google.android.material:material:1.8.0")
            api("io.ktor:ktor-client-okhttp:${ktorVersion}")
            api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutinesVersion}")
         }
      }
      val androidUnitTest by getting
      val iosX64Main by getting
      val iosArm64Main by getting
      val iosSimulatorArm64Main by getting
      val iosMain by creating {
         dependsOn(commonMain)
         iosX64Main.dependsOn(this)
         iosArm64Main.dependsOn(this)
         iosSimulatorArm64Main.dependsOn(this)
      }
      val iosX64Test by getting
      val iosArm64Test by getting
      val iosSimulatorArm64Test by getting
      val iosTest by creating {
         dependsOn(commonTest)
         iosX64Test.dependsOn(this)
         iosArm64Test.dependsOn(this)
         iosSimulatorArm64Test.dependsOn(this)
      }
   }
}

android {
   namespace = "com.github.clockworkclyde.newsapp"
   compileSdk = 33
   defaultConfig {
      minSdk = 24
      targetSdk = 33
   }
}