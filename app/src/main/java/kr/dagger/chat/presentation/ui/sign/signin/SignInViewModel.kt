package kr.dagger.chat.presentation.ui.sign.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.chat.base.SingleLiveEvent
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.usecase.auth.SaveMyUserIdUseCase
import kr.dagger.domain.usecase.auth.SignInGoogleUseCase
import kr.dagger.domain.usecase.auth.UpdateNewUserUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
	private val signInGoogleUseCase: SignInGoogleUseCase,
	private val updateNewUserUseCase: UpdateNewUserUseCase,
	private val saveMyUserIdUseCase: SaveMyUserIdUseCase
) : BaseViewModel() {

	private val _moveMain = SingleLiveEvent<Nothing>()
	val moveMain : LiveData<Nothing>
		get() = _moveMain

	fun googleSignIn2(signInAccount: GoogleSignInAccount) {
		viewModelScope.launch {
			signInGoogleUseCase.invoke(signInAccount.idToken ?: "").collect { response ->
				when (response) {
					is Response.Loading -> setProgress(true)
					is Response.Success -> {
						saveMyUserIdUseCase.invoke(signInAccount.id ?: "")
						setProgress(false)
						updateNewUser(
							User(
								UserInfo(
									id = signInAccount.id ?: "",
									givenName = signInAccount.givenName ?: "",
									displayName = signInAccount.displayName ?: "",
									status = "Newbie",
									profileImageUrl = signInAccount.photoUrl.toString()
								)
							)
						)
					}
					is Response.Error -> {
						setProgress(false)
						setToast(response.errorMessage)
					}
				}
			}
		}
	}

	private fun updateNewUser(user: User) {
		viewModelScope.launch {
			updateNewUserUseCase.invoke(User(user.info)).collect { response ->
				when (response) {
					is Response.Loading -> setProgress(true)
					is Response.Success -> {
						setProgress(false)
						_moveMain.call()
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