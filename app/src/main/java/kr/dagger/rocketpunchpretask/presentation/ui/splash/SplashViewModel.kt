package kr.dagger.rocketpunchpretask.presentation.ui.splash

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.dagger.domain.usecase.auth.GetIsLoggedUserUseCase
import kr.dagger.domain.usecase.auth.GetUserLoginUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
	private val getUserLoginUseCase: GetUserLoginUseCase,
	private val getIsLoggedUserUseCase: GetIsLoggedUserUseCase
)  : ViewModel() {

//	private val _isLoginUser = MutableLiveData<Boolean>()
//	val isLoginUser : LiveData<Boolean> = _isLoginUser

	private val _isLoggedUser = MutableLiveData<Boolean>()
	val isLoggedUser: LiveData<Boolean> = _isLoggedUser

	init {
		viewModelScope.launch {
			_isLoggedUser.value = getIsLoggedUserUseCase.invoke()
		}

		viewModelScope.launch {
			getUserLoginUseCase.invoke().collectLatest {
//				_isLoginUser.value = it
			}
		}
	}

}