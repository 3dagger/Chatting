package kr.dagger.data.repository

import kotlinx.coroutines.flow.Flow
import kr.dagger.data.datastore.UserDataStore
import kr.dagger.domain.repository.UserDataStoreRepository

class UserDataStoreRepositoryImpl(
	private val dataStore: UserDataStore
) : UserDataStoreRepository {

	override fun getLoginUser(): Flow<Boolean> {
		return dataStore.getLoginUser()
	}

	override fun getMyUserId(): Flow<String> {
		return dataStore.getMyUserId()
	}

	override suspend fun saveIsLoginUser(isLoginUser: Boolean) {
		dataStore.setIsLogin(isLoginUser)
	}

	override suspend fun saveMyUserId(id: String) {
		dataStore.setMyUserId(id)
	}
}