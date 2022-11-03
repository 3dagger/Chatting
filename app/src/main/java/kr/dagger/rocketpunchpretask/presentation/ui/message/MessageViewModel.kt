package kr.dagger.rocketpunchpretask.presentation.ui.message

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.dagger.domain.usecase.auth.SaveUserLoginUseCase
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
	private val saveUserLoginUseCase: SaveUserLoginUseCase,
	private val db: FirebaseDatabase
) : ViewModel() {

	init {
//		viewModelScope.launch {
//			Log.d("leeam", "mm :: ${db.reference.child("users").get().await().value}")
////			db.reference.child("users").get().await().children.mapNotNull {
////				Log.d("leeam", "load :: ${it.value}")
////				it.value
////			}
//		}
	}

	fun saveIsLogin(isLogin: Boolean) {
		viewModelScope.launch {
			saveUserLoginUseCase.invoke(isLogin)
		}
	}
}