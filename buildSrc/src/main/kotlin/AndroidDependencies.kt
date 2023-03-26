object AndroidVersions {
   const val kotlinCoreKtx = "1.9.0"
   const val glide = "4.13.2"
   const val adapterDelegates = "4.3.2"
   const val lifecycle = "2.4.0"
   const val navigation = "2.4.2"
   const val ktorVersion = "1.5.0"
}

object AndroidDependencies {
   const val kotlinCoreKtx = "androidx.core:core-ktx:${AndroidVersions.kotlinCoreKtx}"
   const val appCompat = "androidx.appcompat:appcompat:1.6.1"
   const val material = "com.google.android.material:material:1.8.0"
   const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

   //Glide
   const val glide = "com.github.bumptech.glide:glide:${AndroidVersions.glide}"
   const val glideKapt = "com.github.bumptech.glide:compiler:${AndroidVersions.glide}"

   // Adapter Delegates
   const val adapterDelegates =
      "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${AndroidVersions.adapterDelegates}"
   const val adapterDelegatesViewBinding =
      "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${AndroidVersions.adapterDelegates}"

   // Android Ktx
   const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${AndroidVersions.lifecycle}"
   const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${AndroidVersions.lifecycle}"

   // Timber logger
   const val timber = "com.jakewharton.timber:timber:5.0.1"

   // Navigation Component
   const val navigationUiFragmentKtx =
      "androidx.navigation:navigation-fragment-ktx:${AndroidVersions.navigation}"
   const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${AndroidVersions.navigation}"

}