package kr.dagger.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response

interface AuthRepository {
	fun isLoggedInUser(): Boolean

	suspend fun loginUser(idToken: String): Flow<Response<Unit>>

	suspend fun logoutUser(): Flow<Response<Void>>
}