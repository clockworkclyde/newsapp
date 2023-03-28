package com.github.clockworkclyde.newsapp.common.dto

import com.github.clockworkclyde.newsapp.common.InHandler
import com.github.clockworkclyde.newsapp.common.InOutHandler
import com.github.clockworkclyde.newsapp.common.UnitHandler

interface IResult {

   fun isSuccess(): Boolean
   fun isError(): Boolean
   fun isLoading(): Boolean
   fun isEmpty(): Boolean

   fun flatMap(mapper: InOutHandler<IResult>): IResult

   fun <R : Any?> applyOnSuccess(onSuccess: InHandler<R>): IResult
   fun applyOnEmpty(onEmpty: UnitHandler): IResult
}