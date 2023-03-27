package com.github.clockworkclyde.newsapp.di

import org.kodein.di.DI

val kodein = DI {
   import(apiModule)
}