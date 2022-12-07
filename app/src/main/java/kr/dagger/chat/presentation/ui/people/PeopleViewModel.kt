package kr.dagger.chat.presentation.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.usecase.GetAllUserUseCase
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
	private val getAllUserUseCase: GetAllUserUseCase,
) : BaseViewModel() {

	private val _userList = MutableLiveData<List<User>>()
	val userList : LiveData<List<User>>
		get() = _userList

	init {
		getAllUsers()
	}

	fun getAllUsers() {
		viewModelScope.launch {
			getAllUserUseCase.invoke().collectLatest { response ->
				when (response) {
					is Response.Loading -> {
						setProgress(true)
					}
					is Response.Success -> {
						setProgress(false)
						response.data.let {
							_userList.value = it
						}
					}
					is Response.Error -> {
						setProgress(false)
						setToast(response.errorMessage)
					}
				}
			}
		}
	}
}