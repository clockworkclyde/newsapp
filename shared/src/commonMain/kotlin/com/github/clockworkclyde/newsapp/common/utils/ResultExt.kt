@file:Suppress("UNCHECKED_CAST", "unused")

package com.github.clockworkclyde.newsapp.common.utils

import com.github.clockworkclyde.newsapp.common.dto.DResult
import kotlinx.coroutines.flow.map

fun Any?.errorResult(
   code: Int? = null,
   message: String? = null,
   exception: Throwable? = null
) = DResult.ResultThrowable.Error(code = code.orDefault(), message = message, exception = exception)

fun Any?.loadingResult() = DResult.Loading

fun Any?.toEmptySuccess() = DResult.EmptySuccess()

fun Any?.emptyResult() = DResult.Empty

fun <T : Any> Any.successResult(data: T) = DResult.Success<T>(data = data)

fun <T : Any> DResult<T>.get(): T? = this.data

inline fun <reified T : Any, reified R : Any> T.runToSuccess(block: T.() -> DResult.Success<R>) =
   this.let(block)

inline fun <reified T : Any> DResult<T>.applyIfSuccess(block: com.github.clockworkclyde.newsapp.common.InHandler<T>): DResult<T> {
   if (this is DResult.Success) block(this.data)
   return this
}

inline fun <reified T : Any> DResult<T>.applyIfError(block: com.github.clockworkclyde.newsapp.common.InHandler<DResult.ResultThrowable>): DResult<T> {
   if (this is DResult.ResultThrowable) block(this)
   return this
}

inline fun <reified T : Any> DResult<T>.applyIfLoading(block: () -> Unit): DResult<T> {
   if (this is DResult.Loading) block()
   return this
}

inline fun <reified T : Any> DResult<T>.applyIfEmpty(block: () -> Unit): DResult<T> {
   if (this is DResult.Empty) block()
   return this
}

suspend inline fun <reified T : Any> Any.runResultSuspendCatch(
   noinline catch: ((Throwable) -> Unit)? = null,
   crossinline action: suspend () -> DResult<T>
): DResult<T> =
   try {
      action()
   } catch (e: Exception) {
      catch?.invoke(e).errorResult(message = e.message, exception = e)
   }


/* Transformations */

inline fun <T : Any, R : Any> DResult<T>.transformIfSuccess(
   block: T.() -> R
): DResult<R> {
   return if (this is DResult.Success) DResult.Success(block.invoke(this.data))
   else this as DResult<R>
}

suspend inline fun <T : Any, R : Any> DResult<T>.transformIfSuccessSuspended(
   crossinline block: suspend T.() -> R
): DResult<R> {
   return if (this is DResult.Success) DResult.Success(block.invoke(this.data))
   else this as DResult<R>
}

suspend inline fun <T : Any, R : Any> DResult<T>.mapIfSuccessSuspended(
   crossinline block: suspend (T) -> R
): DResult<R> {
   return if (this is DResult.Success) DResult.Success(block.invoke(this.data))
   else this as DResult<R>
}

inline fun <reified T : Any, reified R : Any> DResult<T>.flatMapIfSuccess(
   block: (T) -> DResult<R>
): DResult<R> {
   return if (this is DResult.Success) block(this.data)
   else this as DResult<R>
}

inline fun <reified T : Any, reified R : Any> DResult<T>.flatMapIfEmpty(
   block: () -> DResult<R>
): DResult<R> {
   return if (this is DResult.Empty) block()
   else this as DResult<R>
}

inline fun <reified T : Any, reified R : Any> DResult<T>.flatMapIfError(
   crossinline block: (DResult.ResultThrowable.Error) -> DResult<R>
): DResult<R> {
   return if (this is DResult.ResultThrowable.Error) block(this)
   else this as DResult<R>
}

suspend inline fun <reified T : Any, reified R : Any> DResult<T>.flatMapIfSuccessSuspend(
   crossinline block: suspend (T) -> DResult<R>
): DResult<R> {
   return if (this is DResult.Success) block(this.data)
   else this as DResult<R>
}

inline fun <reified T : Any, reified R : Any> com.github.clockworkclyde.newsapp.common.FlowResult<T>.flatMapFlowIfError(
   crossinline block: (DResult.ResultThrowable.Error) -> DResult<R>
): com.github.clockworkclyde.newsapp.common.FlowResult<R> {
   return this.map {
      it.flatMapIfError(block)
   }
}

inline fun <reified T : Any, reified R : Any> com.github.clockworkclyde.newsapp.common.FlowResult<T>.flatMapFlowIfSuccess(
   crossinline block: (T) -> DResult<R>
): com.github.clockworkclyde.newsapp.common.FlowResult<R> {
   return this.map {
      it.flatMapIfSuccess(block)
   }
}

inline fun <reified T : Any, reified R : Any> com.github.clockworkclyde.newsapp.common.ResultList<T>.mapListTo(
   block: (T) -> R
): com.github.clockworkclyde.newsapp.common.ResultList<R> {
   return when (this) {
      is DResult.Success -> {
         data.map { block(it) }.mapToSuccessResultOrEmpty()
      }
      else -> this as com.github.clockworkclyde.newsapp.common.ResultList<R>
   }
}

inline fun <reified T : List<Any>> T.mapToSuccessResultOrEmpty(): DResult<T> =
   this.takeIf { it.isNotEmpty() }?.toSuccessResult() ?: emptyResult()

inline fun <reified T : Any> T?.toSuccessResult(default: DResult<T> = emptyResult()): DResult<T> =
   this?.let {
      successResult(it)
   } ?: default

inline fun <reified T : Any, reified R : Any> com.github.clockworkclyde.newsapp.common.FlowResultList<T>.mapFlowListTo(
   crossinline block: (T) -> R
): com.github.clockworkclyde.newsapp.common.FlowResultList<R> {
   return this.map { it.mapListTo(block) }
}