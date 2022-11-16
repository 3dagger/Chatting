package kr.dagger.chat.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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