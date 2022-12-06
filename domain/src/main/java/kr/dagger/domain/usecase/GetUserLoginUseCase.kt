package kr.dagger.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.repository.UserDataStoreRepository

class GetUserLoginUseCase(
	private val repository: UserDataStoreRepository
) {
	operator fun invoke(): Flow<Boolean> {
		return repository.getLoginUser()
	}
}