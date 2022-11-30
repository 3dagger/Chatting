package kr.dagger.chat.presentation.ui.sign.siginup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.chat.base.SingleLiveEvent
import kr.dagger.domain.model.Response
import kr.dagger.domain.usecase.auth.SignUpEmailAndPasswordUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
	private val signUpEmailAndPasswordUseCase: SignUpEmailAndPasswordUseCase
) : BaseViewModel() {

	var currentEmailText = MutableLiveData<String>()

	var currentPasswordText = MutableLiveData<String>()

	private val _moveSignIn = SingleLiveEvent<HashMap<String, String>>()
	val moveSignIn : LiveData<HashMap<String, String>>
		get() = _moveSignIn

	fun singUp() {
		viewModelScope.launch {
			signUpEmailAndPasswordUseCase.invoke(email = currentEmailText.value ?: "", password = currentPasswordText.value ?: "").collect { response ->
				when (response) {
					is Response.Loading -> {
						setProgress(true)
					}
					is Response.Success -> {
						Timber.d("in?")
						setProgress(false)
						_moveSignIn.value = hashMapOf(currentEmailText.value!! to currentPasswordText.value!!)
					}
					is Response.Error -> {
						setProgress(false)
						setToast(response.errorMessage)
					}
				}
			}
		}
	}
}