package kr.dagger.data.repository

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.tasks.await
import kr.dagger.data.entitiy.UserEntity
import kr.dagger.data.entitiy.UserInfoEntity
import kr.dagger.data.mapper.UserDtoMapper
import kr.dagger.data.mapper.UserModelToUserEntityMapper
import kr.dagger.data.remote.FirebaseDatabaseDataSource
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.repository.FirebaseDatabaseRepository
import javax.inject.Inject

class FirebaseDatabaseRepositoryImpl(
	private val firebaseDatabaseDataSource: FirebaseDatabaseDataSource
) : FirebaseDatabaseRepository {

	override suspend fun searchUsers(targetName: String): Flow<Response<List<User>>> {
		return firebaseDatabaseDataSource.searchUsers(targetName).transform { response ->
			when (response) {
				is Response.Loading -> {
					emit(Response.Loading)
				}
				is Response.Error -> {
					emit(Response.Error(response.errorMessage))
				}
				is Response.Success -> {
					Log.d("leeam", "response data :: ${response.data}\nfinal :: ${response.data.map { UserDtoMapper().convert(it) }}")
					emit(Response.Success(data = response.data.map { UserDtoMapper().convert(it) }))
				}
			}
		}
	}

	override suspend fun loadUsers(): Flow<Response<List<User>>> {
		return firebaseDatabaseDataSource.loadUsers().transform { response ->
			when (response) {
				is Response.Loading -> {
					emit(Response.Loading)
				}
				is Response.Error -> {
					emit(Response.Error(response.errorMessage))
				}
				is Response.Success -> {
					emit(Response.Success(data = response.data.map { UserDtoMapper().convert(it) }))
				}
			}
		}
	}

	override fun updateNewUser(user: User) {
		firebaseDatabaseDataSource.updateNewUser(UserModelToUserEntityMapper().convert(user))
	}


}
//class FirebaseDatabaseRepositoryImpl @Inject constructor(
//	private val database: FirebaseDatabase
//) : FirebaseDatabaseRepository {
//
//	override suspend fun loadUsers() = flow {
//		try{
//			emit(Response.Loading)
//			database.reference.child("users").get().await().children.mapNotNull { doc ->
//				doc.getValue(User::class.java)
//			}.run {
//				Log.d("leeam", "real :: ${this}")
//				emit(Response.Success(this))
//			}
//		} catch (e: Exception) {
//			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
//		}
//
//	}
//
//
//	override fun updateNewUser(user: User) {
//		database.reference.child("users/${user.info.id}").setValue(user)
//	}
//
//}