package kr.dagger.rocketpunchpretask.presentation.ui.message

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.dagger.domain.usecase.auth.*
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
	private val saveUserLoginUseCase: SaveUserLoginUseCase,
	private val getMyUserIdUseCase: GetMyUserIdUseCase,
	private val getChatUseCase: GetChatUseCase,
	private val getUserInfoUseCase: GetUserInfoUseCase,
	private val getChatListUseCase: GetChatListUseCase
) : ViewModel() {

	private val _myUserId = MutableLiveData<String>()
	val myUserId: LiveData<String> = _myUserId

	init {
		viewModelScope.launch {
			getMyUserIdUseCase.invoke().collect {
				_myUserId.value = it
			}
		}
	}


	fun loadChat() = liveData(Dispatchers.IO) {
		getChatUseCase.invoke().collect { response ->
			emit(response)
		}
	}

	fun loadUserInfo(userId: String) = liveData(Dispatchers.IO) {
		getUserInfoUseCase.invoke(userId).collect { response ->
			emit(response)
		}
	}

	fun loadChatList() = liveData(Dispatchers.IO) {
		getChatListUseCase.invoke().collect { response ->
			emit(response)
		}
	}

	fun saveIsLogin(isLogin: Boolean) {
		viewModelScope.launch {
			saveUserLoginUseCase.invoke(isLogin)
		}
	}
}