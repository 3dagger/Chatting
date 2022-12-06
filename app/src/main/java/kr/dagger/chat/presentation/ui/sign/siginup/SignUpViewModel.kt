package kr.dagger.chat.presentation.ui.sign.siginup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.chat.base.SingleLiveEvent
import kr.dagger.chat.presentation.ui.sign.SignRequired
import kr.dagger.domain.model.Response
import kr.dagger.domain.usecase.sign.SignUpEmailAndPasswordUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
	private val signUpEmailAndPasswordUseCase: SignUpEmailAndPasswordUseCase
) : BaseViewModel() {

	var currentDisplayName = MutableLiveData<String>()

	var currentEmailText = MutableLiveData<String>()

	var currentPasswordText = MutableLiveData<String>()

	private val _moveSignIn = SingleLiveEvent<HashMap<SignRequired, String>>()
	val moveSignIn : LiveData<HashMap<SignRequired, String>>
		get() = _moveSignIn

	fun singUp() {
		viewModelScope.launch {
			if (currentDisplayName.value.isNullOrBlank() || currentEmailText.value.isNullOrBlank() || currentPasswordText.value.isNullOrBlank()) {
				setSnack("아이디 또는 비밀번호를 확인해주세요.")
			} else {
				signUpEmailAndPasswordUseCase.invoke(email = currentEmailText.value!!, password = currentPasswordText.value!!, displayName = currentDisplayName.value!!).collect { response ->
					when (response) {
						is Response.Loading -> {
							setProgress(true)
						}
						is Response.Success -> {
							setProgress(false)
							_moveSignIn.value = hashMapOf(
								SignRequired.Email to currentEmailText.value!!,
								SignRequired.Password to currentPasswordText.value!!
							)
						}
						is Response.Error -> {
							setProgress(false)
							setSnack(response.errorMessage)
						}
					}
				}
			}
		}
	}
}