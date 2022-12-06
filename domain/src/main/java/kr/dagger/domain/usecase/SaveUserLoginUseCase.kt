package kr.dagger.domain.usecase

import kr.dagger.domain.repository.UserDataStoreRepository

class SaveUserLoginUseCase(
	private val repository: UserDataStoreRepository
) {
	suspend operator fun invoke(isLogin: Boolean) {
		repository.saveIsLoginUser(isLogin)
	}
}