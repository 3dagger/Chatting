package kr.dagger.rocketpunchpretask.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.dagger.domain.usecase.auth.SignOutUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
	private val signOutUseCase: SignOutUseCase
)  : ViewModel() {

	fun signOut() = liveData(Dispatchers.IO) {
			signOutUseCase.invoke().collect { response ->
				emit(response)
			}
		}

}