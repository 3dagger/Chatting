package kr.dagger.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kr.dagger.domain.repository.UserDataStoreRepository
import javax.inject.Inject

class UserDataStore @Inject constructor(
	private val dataStore: DataStore<Preferences>
) {

	companion object {
		private val KEY_IS_LOGIN_USER = booleanPreferencesKey("is_login_user")
	}

	fun getLoginUser() = dataStore.data.map { preferences ->
			preferences[KEY_IS_LOGIN_USER] ?: false
		}

	suspend fun setIsLogin(isLoginUser: Boolean) {
		dataStore.edit { preferences ->
			preferences[KEY_IS_LOGIN_USER] = isLoginUser
		}
	}
}