package com.github.clockworkclyde.newsapp.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.clockworkclyde.androidcore.presentation.activities.NavHostActivity
import com.github.clockworkclyde.newsapp.Greeting

class MainActivity : NavHostActivity(R.layout.activity_main) {

   override val hostFragmentId: Int = R.id.mainHostFragment

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      //Greeting().greet()
   }
}
