package com.github.clockworkclyde.androidcore.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.net.Uri
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.clockworkclyde.androidcore.presentation.fragments.IBaseFragment
import com.github.clockworkclyde.androidcore.presentation.viewmodels.IEventViewModel
import com.github.clockworkclyde.newsapp.common.dto.IEvent
import com.github.clockworkclyde.newsapp.common.dto.UEvent
import kotlinx.coroutines.launch

fun Fragment.launchAndRepeatOnState(state: Lifecycle.State, block: suspend () -> Unit) {
   viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(state) {
         block()
      }
   }
}

fun AppCompatActivity.launchAndRepeatOnState(state: Lifecycle.State, block: suspend () -> Unit) {
   lifecycleScope.launch {
      repeatOnLifecycle(state) {
         block()
      }
   }
}

inline fun <reified VM : IEventViewModel> IBaseFragment<VM>.fetch() =
   this.viewModel?.processEvent(UEvent.Fetch)

inline fun <ViewDataBinding, reified VM : IEventViewModel> IBaseFragment<VM>.processEvent(
   event: IEvent
) =
   this.viewModel?.processEvent(event)

inline fun EditText.onTextChanged(crossinline listener: (String) -> Unit) {
   doOnTextChanged { text, _, _, _ ->
      //if (this.hasError()) this.clearError()
      listener(text?.toString().orEmpty())
   }
}

fun EditText.clearError() {
   this.error = null
}

fun EditText.hasError(): Boolean = this.error != null

fun Fragment.toast(message: Any? = "", duration: Int = Toast.LENGTH_SHORT) =
   Toast.makeText(requireContext(), message.toString(), duration).show()

fun ViewGroup.setClipToOutline() {
   this.clipToOutline = true
}

fun View.hideKeyboard(): Boolean {
   try {
      val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
      return imm.hideSoftInputFromWindow(windowToken, 0)
   } catch (ignored: RuntimeException) {
   }
   return false
}

fun Any.alertDialog(
   context: Context,
   message: String,
   title: String = "Title",
   onSubmitText: String = "OK",
   onCancelText: String = "Cancel",
   onSubmit: (() -> Unit)? = null,
   onCancel: () -> Unit = {}
) {
   val resources = context.resources
   return AlertDialog.Builder(context)
      .setTitle(title)
      .setMessage(message)
      .apply {
         if (onSubmit != null) {
            this.setPositiveButton(onSubmitText) { _, _ ->
               onSubmit()
            }
         }
      }
      .setNegativeButton(onCancelText) { dialog, _ ->
         dialog.dismiss()
         onCancel()
      }
      .create()
      .show()
}

fun Uri.asBitmap(activity: Activity): Bitmap =
   MediaStore.Images.Media.getBitmap(activity.contentResolver, this)

fun Activity.hideKeyboard(): Boolean {
   val view = currentFocus ?: View(this)
   if (view is EditText) view.clearFocus()
   return view.hideKeyboard()
}

fun TextView.setTextOrGone(text: CharSequence?) {
   this.isGone = text.isNullOrEmpty()
   this.text = text
}

fun getScreenSize(context: Context): Point {
   val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
   val size = Point()
   display.getSize(size)
   return size
}

inline fun Any.postDelayed(
   delayMs: Long = 250L,
   crossinline action: () -> Unit
) {
   Handler().postDelayed({ action() }, delayMs)
}