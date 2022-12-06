package kr.dagger.chat.presentation.ui.splash

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.dagger.domain.usecase.sign.GetIsLoggedUserUseCase
import kr.dagger.domain.usecase.GetUserLoginUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
	private val getUserLoginUseCase: GetUserLoginUseCase,
	private val getIsLoggedUserUseCase: GetIsLoggedUserUseCase
)  : ViewModel() {

	private val _isLoggedUser = MutableLiveData<Boolean>()
	val isLoggedUser: LiveData<Boolean> = _isLoggedUser

	init {
		viewModelScope.launch {
			_isLoggedUser.value = getIsLoggedUserUseCase.invoke()
		}

		viewModelScope.launch {
			getUserLoginUseCase.invoke().collectLatest {
			}
		}
	}

}