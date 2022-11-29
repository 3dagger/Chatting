package kr.dagger.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor(
	private val dataStore: DataStore<Preferences>
) {

	companion object {
		private val KEY_IS_LOGIN_USER = booleanPreferencesKey("is_login_user")
		private val KEY_MY_USER_ID = stringPreferencesKey("my_user_id")
	}

	fun getLoginUser() = dataStore.data.map { preferences ->
			preferences[KEY_IS_LOGIN_USER] ?: false
		}

	suspend fun setIsLogin(isLoginUser: Boolean) {
		dataStore.edit { preferences ->
			preferences[KEY_IS_LOGIN_USER] = isLoginUser
		}
	}

	fun getMyUserId() = dataStore.data.map { preferences ->
		preferences[KEY_MY_USER_ID] ?: ""
	}

	suspend fun setMyUserId(id: String) {
		dataStore.edit { preferences ->
			preferences[KEY_MY_USER_ID] = id
		}
	}
}