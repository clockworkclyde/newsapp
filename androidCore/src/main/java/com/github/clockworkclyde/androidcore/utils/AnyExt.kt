package com.github.clockworkclyde.androidcore.utils

fun <T: Boolean?> T.orFalse(): Boolean = this ?: false

fun <T: Boolean?> T.orTrue(): Boolean = this ?: true