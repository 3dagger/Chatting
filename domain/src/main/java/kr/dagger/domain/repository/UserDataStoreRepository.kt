package kr.dagger.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserDataStoreRepository {

	fun getLoginUser(): Flow<Boolean>

	suspend fun saveIsLoginUser(isLoginUser: Boolean)
}