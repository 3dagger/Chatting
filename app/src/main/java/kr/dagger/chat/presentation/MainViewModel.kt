package kr.dagger.chat.presentation

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.domain.usecase.SaveMyUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val firebaseAuth: FirebaseAuth,
	private val saveMyUserIdUseCase: SaveMyUserIdUseCase
) : BaseViewModel() {

	fun saveUid() {
		viewModelScope.launch {
			saveMyUserIdUseCase.invoke(firebaseAuth.uid ?: "")
		}
	}
}