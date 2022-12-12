package kr.dagger.chat.presentation.ui.sign.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.chat.base.SingleLiveEvent
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.usecase.sign.SignInEmailAndPasswordUseCase
import kr.dagger.domain.usecase.sign.SignInGoogleUseCase
import kr.dagger.domain.usecase.sign.UpdateNewUserUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
	private val signInGoogleUseCase: SignInGoogleUseCase,
	private val signInEmailAndPasswordUseCase: SignInEmailAndPasswordUseCase,
	private val updateNewUserUseCase: UpdateNewUserUseCase
) : BaseViewModel() {

	var currentEmailText = MutableLiveData<String>()

	var currentPasswordText = MutableLiveData<String>()

	private val _moveMain = SingleLiveEvent<Nothing>()
	val moveMain : LiveData<Nothing>
		get() = _moveMain

	fun googleSignIn(signInAccount: GoogleSignInAccount) {
		viewModelScope.launch {
			signInGoogleUseCase.invoke(signInAccount.idToken ?: "").collect { response ->
				when (response) {
					is Response.Loading -> {
						setProgress(true)
					}
					is Response.Success -> {
						setProgress(false)
						updateNewUser(
							User(
								UserInfo(
									id = response.data.id,
									givenName = response.data.givenName,
									displayName = response.data.displayName,
									status = "Newbie",
									profileImageUrl = response.data.profileImageUrl
								)
							)
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

	fun emailAndPasswordSignIn(email: String?, password: String?) {
		viewModelScope.launch {
			if (currentEmailText.value.isNullOrBlank() || currentPasswordText.value.isNullOrBlank()) {
				setSnack("아이디 또는 비밀번호를 확인해주세요.")
			} else {
				signInEmailAndPasswordUseCase.invoke(email!!, password!!).collect { response ->
					when (response) {
						is Response.Loading -> {
							setProgress(true)
						}
						is Response.Success -> {
							setProgress(false)
							_moveMain.call()
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

	private fun updateNewUser(user: User) {
		viewModelScope.launch {
			updateNewUserUseCase.invoke(User(user.info)).collect { response ->
				when (response) {
					is Response.Loading -> {
						setProgress(true)
					}
					is Response.Success -> {
						setProgress(false)
						_moveMain.call()
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