package kr.dagger.chat.presentation.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.dagger.chat.base.BaseViewModel
import kr.dagger.chat.base.SingleLiveEvent
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.usecase.GetAllUserUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
	private val getAllUserUseCase: GetAllUserUseCase,
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
			getAllUserUseCase.invoke().collectLatest { response ->
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
							Timber.d("it :: $it")
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