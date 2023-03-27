package com.github.clockworkclyde.androidcore.common

interface IPermissionManager {
   fun checkPermissionGranted(permission: String): Boolean
   fun checkMultipleGranted(vararg permissions: String): Map<String, Boolean>
}