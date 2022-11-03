package kr.dagger.rocketpunchpretask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.dagger.domain.repository.AuthRepository
import kr.dagger.domain.repository.FirebaseDatabaseRepository
import kr.dagger.domain.repository.UserDataStoreRepository
import kr.dagger.domain.usecase.auth.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

	@Provides
	@Singleton
	fun provideGetGetIsLoggedUserUseCase(repository: AuthRepository): GetIsLoggedUserUseCase {
		return GetIsLoggedUserUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideSignOutUseCase(repository: AuthRepository): SignOutUseCase {
		return SignOutUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideSignInUseCase(repository: AuthRepository): SignInUseCase {
		return SignInUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideUpdateNewUserUseCase(repository: FirebaseDatabaseRepository): UpdateNewUserUseCase {
		return UpdateNewUserUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideGetAllUserUseCase(repository: FirebaseDatabaseRepository): GetAllUserUseCase {
		return GetAllUserUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideSearchUserUseCase(repository: FirebaseDatabaseRepository): SearchUserUseCase {
		return SearchUserUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideSaveUserLoginUseCase(repository: UserDataStoreRepository): SaveUserLoginUseCase {
		return SaveUserLoginUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideGetUserLoginUseCase(repository: UserDataStoreRepository): GetUserLoginUseCase {
		return GetUserLoginUseCase(repository)
	}



}