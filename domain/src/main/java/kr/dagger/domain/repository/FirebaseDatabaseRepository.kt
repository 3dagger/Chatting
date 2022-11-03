package kr.dagger.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User

interface FirebaseDatabaseRepository {

	suspend fun searchUsers(targetName: String): Flow<Response<List<User>>>

	suspend fun loadUsers(): Flow<Response<List<User>>>

	fun updateNewUser(user: User)
}