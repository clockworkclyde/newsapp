package com.github.clockworkclyde.androidcore.utils

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

interface IResourcesProvider {

   fun getString(@StringRes id: Int): String
   fun getStringArray(@ArrayRes arrayId: Int): Array<String>
   fun getString(@StringRes id: Int, vararg formatArgs: Any): String
}