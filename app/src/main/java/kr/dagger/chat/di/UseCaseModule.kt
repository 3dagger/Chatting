package kr.dagger.chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.dagger.domain.repository.AuthRepository
import kr.dagger.domain.repository.FirebaseDatabaseRepository
import kr.dagger.domain.repository.UserDataStoreRepository
import kr.dagger.domain.usecase.*
import kr.dagger.domain.usecase.sign.*
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
	fun provideSignUpEmailAndPasswordUseCase(repository: AuthRepository): SignUpEmailAndPasswordUseCase {
		return SignUpEmailAndPasswordUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideSignOutUseCase(repository: AuthRepository): SignOutUseCase {
		return SignOutUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideSignInEmailAndPasswordUseCase(repository: AuthRepository): SignInEmailAndPasswordUseCase {
		return SignInEmailAndPasswordUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideSignInGoogleUseCase(repository: AuthRepository): SignInGoogleUseCase {
		return SignInGoogleUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideUpdateNewUserUseCase(repository: FirebaseDatabaseRepository): UpdateNewUserUseCase {
		return UpdateNewUserUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideUpdateNewChatUseCase(repository: FirebaseDatabaseRepository): UpdateNewChatUseCase {
		return UpdateNewChatUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideGetChatUseCase(repository: FirebaseDatabaseRepository): GetChatUseCase {
		return GetChatUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideGetUserInfoUseCase(repository: FirebaseDatabaseRepository): GetUserInfoUseCase {
		return GetUserInfoUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideGetChatListUseCase(repository: FirebaseDatabaseRepository): GetChatListUseCase {
		return GetChatListUseCase(repository)
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

	@Provides
	@Singleton
	fun provideSaveMyUserIdUseCase(repository: UserDataStoreRepository): SaveMyUserIdUseCase {
		return SaveMyUserIdUseCase(repository)
	}

	@Provides
	@Singleton
	fun provideGetMyUserIdUseCase(repository: UserDataStoreRepository): GetMyUserIdUseCase {
		return GetMyUserIdUseCase(repository)
	}
}