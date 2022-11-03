package kr.dagger.rocketpunchpretask.di

import android.content.Context
import android.preference.Preference
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.dagger.data.datastore.UserDataStore

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

	private const val DATA_STORE_NAME = "rocketpunch_app_data_store"

	private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

	@Provides
	fun provideUserDataStore(@ApplicationContext context: Context): UserDataStore {
		return UserDataStore(context.dataStore)
	}
}