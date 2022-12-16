package kr.dagger.chat.presentation.ui.mypage

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.chat.base.SingleLiveEvent
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.usecase.GetMyUserIdUseCase
import kr.dagger.domain.usecase.GetUserInfoUseCase
import kr.dagger.domain.usecase.sign.SignOutUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
	private val signOutUseCase: SignOutUseCase,
	private val getUserInfoUseCase: GetUserInfoUseCase,
	private val getMyUserIdUseCase: GetMyUserIdUseCase
)  : BaseViewModel() {

	private val _userInfo = MutableLiveData<UserInfo?>()
	val userIfo : LiveData<UserInfo?>
		get() = _userInfo

	private val _profileUri = MutableLiveData<Uri>()
	val profileUri : LiveData<Uri>
		get() = _profileUri

	private val _moveSignIn = SingleLiveEvent<Any>()
	val moveSignIn : LiveData<Any>
		get() = _moveSignIn

	fun changeProfileUri(uri: Uri) {
		_profileUri.value = uri
	}

	fun getUserInfo() {
		viewModelScope.launch {
			getMyUserIdUseCase.invoke().collect { uid ->
				getUserInfoUseCase.invoke(uid).collect { response ->
					when (response) {
						is Response.Loading -> {
							setProgress(true)
						}
						is Response.Success -> {
							setProgress(false)
							_userInfo.value = response.data
							_profileUri.value = response.data.profileImageUrl.toUri()
						}
						is Response.Error -> {
							setProgress(false)
						}
					}
				}
			}
		}
	}

	fun signOut() {
		viewModelScope.launch {
			signOutUseCase.invoke().collect { response ->
				when (response) {
					is Response.Loading -> {
						setProgress(true)
					}
					is Response.Success -> {
						setProgress(false)
						_moveSignIn.call()
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