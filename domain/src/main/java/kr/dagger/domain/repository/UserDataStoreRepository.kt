package kr.dagger.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserDataStoreRepository {

	fun getLoginUser(): Flow<Boolean>

	fun getMyUserId(): Flow<String>

	suspend fun saveIsLoginUser(isLoginUser: Boolean)

	suspend fun saveMyUserId(id: String)
}