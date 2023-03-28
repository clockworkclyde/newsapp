package com.github.clockworkclyde.androidcore.utils

interface IPermissionManager {
   fun checkPermissionGranted(permission: String): Boolean
   fun checkMultipleGranted(vararg permissions: String): Map<String, Boolean>
}