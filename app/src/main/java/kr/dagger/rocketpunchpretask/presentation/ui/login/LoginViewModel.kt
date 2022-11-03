package kr.dagger.rocketpunchpretask.presentation.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.dagger.domain.model.User
import kr.dagger.domain.usecase.auth.SaveMyUserIdUseCase
import kr.dagger.domain.usecase.auth.SignInUseCase
import kr.dagger.domain.usecase.auth.UpdateNewUserUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val signInUseCase: SignInUseCase,
	private val updateNewUserUseCase: UpdateNewUserUseCase,
	private val saveMyUserIdUseCase: SaveMyUserIdUseCase
) : ViewModel(){

	fun googleSignIn(idToken: String) = liveData(Dispatchers.IO) {
		signInUseCase.invoke(idToken).collect { response ->
			emit(response)
		}
	}

	fun updateNewUser(user: User) {
		updateNewUserUseCase.updateNewUser(
			User(info = user.info)
		)
	}

	fun saveMyUserId(id: String) {
		viewModelScope.launch {
			saveMyUserIdUseCase.invoke(id)
		}
	}
}