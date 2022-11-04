package kr.dagger.rocketpunchpretask.di

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.dagger.data.datastore.UserDataStore
import kr.dagger.data.remote.FirebaseDatabaseDataSource
import kr.dagger.data.repository.AuthRepositoryImpl
import kr.dagger.data.repository.FirebaseDatabaseRepositoryImpl
import kr.dagger.data.repository.UserDataStoreRepositoryImpl
import kr.dagger.domain.repository.AuthRepository
import kr.dagger.domain.repository.FirebaseDatabaseRepository
import kr.dagger.domain.repository.UserDataStoreRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideAuthRepository(
		googleSignInClient: GoogleSignInClient,
		auth: FirebaseAuth
	): AuthRepository {
		return AuthRepositoryImpl(googleSignInClient, auth)
	}

	@Provides
	@Singleton
	fun provideUserDataStoreRepository(
		dataStore: UserDataStore
	): UserDataStoreRepository {
		return UserDataStoreRepositoryImpl(dataStore)
	}

	@Provides
	@Singleton
	fun provideFirebaseDataBaseRepository(
		firebaseDatabaseDataSource: FirebaseDatabaseDataSource
	) : FirebaseDatabaseRepository {
		return FirebaseDatabaseRepositoryImpl(firebaseDatabaseDataSource)
	}
}