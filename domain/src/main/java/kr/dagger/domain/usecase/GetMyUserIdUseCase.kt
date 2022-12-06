package kr.dagger.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.repository.UserDataStoreRepository

class GetMyUserIdUseCase(
	private val repository: UserDataStoreRepository
) {
	operator fun invoke(): Flow<String> {
		return repository.getMyUserId()
	}
}