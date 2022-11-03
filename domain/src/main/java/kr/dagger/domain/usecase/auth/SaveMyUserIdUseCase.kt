package kr.dagger.domain.usecase.auth

import kr.dagger.domain.repository.UserDataStoreRepository

class SaveMyUserIdUseCase(
	private val repository: UserDataStoreRepository
) {

	suspend operator fun invoke(id: String) {
		repository.saveMyUserId(id)
	}
}