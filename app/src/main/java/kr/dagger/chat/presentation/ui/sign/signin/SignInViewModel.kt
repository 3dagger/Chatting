package kr.dagger.chat.presentation.ui.sign.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.dagger.domain.model.User
import kr.dagger.domain.usecase.auth.SaveMyUserIdUseCase
import kr.dagger.domain.usecase.auth.SignInGoogleUseCase
import kr.dagger.domain.usecase.auth.UpdateNewUserUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
	private val signInGoogleUseCase: SignInGoogleUseCase,
	private val updateNewUserUseCase: UpdateNewUserUseCase,
	private val saveMyUserIdUseCase: SaveMyUserIdUseCase
) : ViewModel() {

	fun googleSignIn(idToken: String) = liveData(Dispatchers.IO) {
		signInGoogleUseCase.invoke(idToken).collectLatest { response ->
			emit(response)
		}
	}

	fun updateNewUser(user: User) = liveData(Dispatchers.IO) {
		updateNewUserUseCase.invoke(User(user.info)).collectLatest { response ->
			emit(response)
		}
	}

	fun saveMyUserId(id: String) {
		viewModelScope.launch {
			saveMyUserIdUseCase.invoke(id)
		}
	}
}