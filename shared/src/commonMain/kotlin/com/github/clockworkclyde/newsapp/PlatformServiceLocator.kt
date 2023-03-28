package com.github.clockworkclyde.newsapp

import io.ktor.client.engine.*

expect object PlatformServiceLocator {
   val httpClientEngine: HttpClientEngine
}