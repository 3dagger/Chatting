package kr.dagger.chat.presentation.ui.sign.siginup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.dagger.domain.model.Response
import kr.dagger.domain.usecase.auth.SignUpEmailAndPasswordUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
	private val signUpEmailAndPasswordUseCase: SignUpEmailAndPasswordUseCase
) : ViewModel() {

	var currentEmailText = MutableLiveData<String>()

	var currentPasswordText = MutableLiveData<String>()

	private val _isProgress = MutableLiveData<Boolean>()
	val isProgress: LiveData<Boolean>
		get() = _isProgress

	private val _toastMessage = MutableLiveData<String>()
	val toastMessage: LiveData<String>
		get() = _toastMessage


	fun singUp() {
		viewModelScope.launch {
			signUpEmailAndPasswordUseCase.invoke(email = currentEmailText.value ?: "", password = currentPasswordText.value ?: "").collect { response ->
				when (response) {
					is Response.Loading -> {
						Timber.d("loading")
						_isProgress.value = true
					}

					is Response.Success -> {
						Timber.d("success :: ${response.data}")
						_isProgress.value = false
					}

					is Response.Error -> {
						Timber.d("error :: ${response.errorMessage}")
						_isProgress.value = false
						_toastMessage.value = response.errorMessage
					}
				}
			}
		}
	}

}