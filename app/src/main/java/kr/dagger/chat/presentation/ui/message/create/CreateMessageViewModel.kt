package kr.dagger.chat.presentation.ui.message.create

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.dagger.domain.model.Chat
import kr.dagger.domain.usecase.GetAllUserUseCase
import kr.dagger.domain.usecase.GetMyUserIdUseCase
import kr.dagger.domain.usecase.SearchUserUseCase
import kr.dagger.domain.usecase.UpdateNewChatUseCase
import javax.inject.Inject

@HiltViewModel
class CreateMessageViewModel @Inject constructor(
	private val getAllUserUseCase: GetAllUserUseCase,
	private val searchUserUseCase: SearchUserUseCase,
	private val getMyUserIdUseCase: GetMyUserIdUseCase,
	private val updateNewChatUseCase: UpdateNewChatUseCase
) : ViewModel() {

	private val _searchData = MutableLiveData<String>()
	val searchData: MutableLiveData<String> = _searchData

	private val _myUserId = MutableLiveData<String>()
	val myUserId: LiveData<String> = _myUserId

	init {
		viewModelScope.launch {
			getMyUserIdUseCase.invoke().collect {
				_myUserId.value = it
			}
		}

//		viewModelScope.launch {
//			getAllUserUseCase.invoke().collect { response ->
//				Timber.d("response :: $response")
//			}
//		}
	}

	fun searchUsers(targetName: String) = liveData(Dispatchers.IO) {
		searchUserUseCase.invoke(targetName).collect { response ->
			emit(response)
		}
	}

//	fun getLoadAllUser() = liveData(Dispatchers.IO) {
//		getAllUserUseCase.invoke().collect { response ->
//			emit(response)
//		}
//	}

	fun updateNewChat(chat: Chat) {
		updateNewChatUseCase.updateNewChat(chat)
	}

}