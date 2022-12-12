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
import kr.dagger.domain.usecase.GetMyUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
	private val getAllUserUseCase: GetAllUserUseCase,
	private val getMyUserIdUseCase: GetMyUserIdUseCase
) : BaseViewModel() {

	private val _userList = MutableLiveData<List<User>>()
	val userList : LiveData<List<User>>
		get() = _userList

	private val _isRefresh = MutableLiveData<Boolean>()
	val isRefresh : LiveData<Boolean>
		get() = _isRefresh

	init {
		getAllUsers()
	}

	fun getAllUsers(isRefresh: Boolean = false) {
		viewModelScope.launch {
			getMyUserIdUseCase.invoke().collect { uid ->
				getAllUserUseCase.invoke(uid).collectLatest { response ->
					when (response) {
						is Response.Loading -> {
							if (isRefresh) {
								setProgress(false)
								_isRefresh.value = true
							} else {
								setProgress(true)
								_isRefresh.value = false
							}
						}
						is Response.Success -> {
							setProgress(false)
							_isRefresh.value = false
							response.data.let {
								_userList.value = it
							}
						}
						is Response.Error -> {
							setProgress(false)
							_isRefresh.value = false
							setSnack(response.errorMessage)
						}
					}
				}
			}
		}
	}
}