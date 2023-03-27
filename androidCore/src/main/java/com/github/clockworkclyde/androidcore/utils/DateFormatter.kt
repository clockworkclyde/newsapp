package com.github.clockworkclyde.androidcore.utils

import java.util.*

fun Long.toDate(): String {
   val c = Calendar.getInstance()
   c.timeInMillis = this

   val d = c.get(Calendar.DAY_OF_MONTH)
   val m = c.get(Calendar.MONTH)
   val y = c.get(Calendar.YEAR)

   return ("$d $m, $y")
}