package com.github.clockworkclyde.newsapp.common.utils

fun Int?.orDefault() = this ?: 0

fun Int?.orLessZero() = this ?: -1