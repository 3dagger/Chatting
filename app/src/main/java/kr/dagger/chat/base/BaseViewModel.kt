package kr.dagger.chat.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

	private val _isProgress = MutableLiveData<Boolean>()
	val isProgress : LiveData<Boolean>
		get() = _isProgress

	private val _snackMessage = MutableLiveData<String>()
	val snackMessage : LiveData<String>
		get() = _snackMessage

	fun setProgress(value: Boolean) {
		_isProgress.value = value
	}

	fun setSnack(value: String) {
		_snackMessage.value = value
	}
}