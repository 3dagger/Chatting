package kr.dagger.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class GetUserInfoUseCase(
	private val repository: FirebaseDatabaseRepository
) {
	suspend operator fun invoke(userId: String): Flow<Response<UserInfo>> {
		return repository.loadUserInfo(userId)
	}
}