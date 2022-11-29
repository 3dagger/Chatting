package kr.dagger.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.repository.AuthRepository

class SignUpEmailAndPasswordUseCase(
	private val repository: AuthRepository
) {
	suspend operator fun invoke(email: String, password: String): Flow<Response<Unit>> {
		return repository.createUser(email, password)
	}
}