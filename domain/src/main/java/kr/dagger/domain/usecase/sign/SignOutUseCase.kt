package kr.dagger.domain.usecase.sign

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.repository.AuthRepository

class SignOutUseCase(
	private val repository: AuthRepository
) {
	suspend operator fun invoke(): Flow<Response<Unit>> {
		return repository.logoutUser()
	}
}