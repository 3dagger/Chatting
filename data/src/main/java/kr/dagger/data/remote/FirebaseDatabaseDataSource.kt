package kr.dagger.data.remote

import kotlinx.coroutines.flow.Flow
import kr.dagger.data.entitiy.UserEntity
import kr.dagger.domain.model.Response


interface FirebaseDatabaseDataSource {

	suspend fun searchUsers(targetName: String): Flow<Response<List<UserEntity>>>

	suspend fun loadUsers(): Flow<Response<List<UserEntity>>>

	fun updateNewUser(user: UserEntity)
}