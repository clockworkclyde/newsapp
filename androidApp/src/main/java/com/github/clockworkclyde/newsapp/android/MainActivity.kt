package com.github.clockworkclyde.newsapp.android
import android.os.Bundle
import com.github.clockworkclyde.androidcore.presentation.activities.NavHostActivity

class MainActivity : NavHostActivity(R.layout.activity_main) {

   override val hostFragmentId: Int = R.id.mainHostFragment

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }
}
