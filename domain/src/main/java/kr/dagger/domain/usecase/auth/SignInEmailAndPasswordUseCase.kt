package kr.dagger.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.repository.AuthRepository

class SignInEmailAndPasswordUseCase(
	private val repository: AuthRepository
) {
	suspend operator fun invoke(email: String, password: String) : Flow<Response<UserInfo>> {
		return repository.signInEmailAndPassword(email, password)
	}
}