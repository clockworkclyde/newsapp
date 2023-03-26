package com.github.clockworkclyde.androidcore.utils

fun Int?.orDefault() = this ?: 0

fun Int?.orLessZero() = this ?: -1