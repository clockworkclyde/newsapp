package com.github.clockworkclyde.newsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform