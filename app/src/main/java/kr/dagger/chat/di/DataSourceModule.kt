package kr.dagger.chat.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.dagger.data.remote.FirebaseDatabaseDataSource
import kr.dagger.data.remote.FirebaseDatabaseDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

	@Provides
	@Singleton
	fun provideFirebaseDatabaseDataSource(database: FirebaseDatabase) : FirebaseDatabaseDataSource {
		return FirebaseDatabaseDataSourceImpl(database)
	}

}