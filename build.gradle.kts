plugins {
    id("com.android.application").version("7.4.2").apply(false)
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)
    kotlin("plugin.serialization").version("1.8.0").apply(false)
    id("androidx.navigation").version("2.5.3").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
