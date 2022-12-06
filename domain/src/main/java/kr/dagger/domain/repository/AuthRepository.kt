package kr.dagger.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.UserInfo

interface AuthRepository {
	fun isLoggedInUser(): Boolean

	suspend fun createUser(email: String, password: String, displayName: String): Flow<Response<UserInfo>>

	suspend fun signInEmailAndPassword(email: String, password: String): Flow<Response<Unit>>

	suspend fun signInGoogle(idToken: String): Flow<Response<UserInfo>>

	suspend fun logoutUser(): Flow<Response<Unit>>
}