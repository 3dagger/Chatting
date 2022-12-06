package kr.dagger.chat.presentation.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
	val intent = Intent(this, it)
	intent.putExtras(Bundle().apply(extras))
	startActivity(intent)
}

fun Context.toast(message: String) {
	Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
	this.visibility = View.VISIBLE
}

fun View.gone() {
	this.visibility = View.GONE
}

fun View.inVisible() {
	this.visibility = View.INVISIBLE
}

fun showSnackBar(view: View, message: String, action: (Snackbar.() -> Unit)? = null) {
	Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
		action?.let { it() }
		show()
	}
}

fun deFocusAndHideKeyboard(activity: Activity?) {
	if (activity == null) return
	val view = activity.currentFocus
	if (view != null) {
		view.clearFocus()
		hideKeyboard(activity, view.windowToken)
	}
}

fun View.forceHideKeyboard() {
	val inputManager: InputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	inputManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

private fun hideKeyboard(activity: Activity?, windowToken: IBinder?) {
	if (activity == null) return
	val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	inputManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}