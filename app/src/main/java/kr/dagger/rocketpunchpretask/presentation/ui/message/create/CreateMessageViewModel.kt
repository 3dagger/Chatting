package kr.dagger.rocketpunchpretask.presentation.ui.message.create

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.dagger.domain.usecase.auth.GetAllUserUseCase
import kr.dagger.domain.usecase.auth.SearchUserUseCase
import javax.inject.Inject

@HiltViewModel
class CreateMessageViewModel @Inject constructor(
	private val getAllUserUseCase: GetAllUserUseCase,
	private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

	private val _searchData = MutableLiveData<String>()
	val searchData: MutableLiveData<String> = _searchData

	fun searchUsers(targetName: String) = liveData(Dispatchers.IO) {
		searchUserUseCase.invoke(targetName).collect { response ->
			emit(response)
		}
	}

	fun getLoadAllUser() = liveData(Dispatchers.IO) {
		getAllUserUseCase.invoke().collect { response ->
			emit(response)
		}
	}

}