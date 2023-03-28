package com.github.clockworkclyde.newsapp.common.utils

fun <T: Boolean?> T.orFalse(): Boolean = this ?: false

fun <T: Boolean?> T.orTrue(): Boolean = this ?: true