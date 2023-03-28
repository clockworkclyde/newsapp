package com.github.clockworkclyde.androidcore.utils

import android.content.Context
import com.github.clockworkclyde.androidcore.R
import java.util.*
import java.util.concurrent.TimeUnit

fun Long.secondsToDate(context: Context): String {
   val c = Calendar.getInstance()
   c.timeInMillis = TimeUnit.SECONDS.toMillis(this)
   val months = context.resources.getStringArray(R.array.ru_articles_months_array)

   val dd = c.get(Calendar.DAY_OF_MONTH)
   val mm = c.get(Calendar.MONTH)
   val yyyy = c.get(Calendar.YEAR)

   return "$dd ${months[mm]}, $yyyy"
}