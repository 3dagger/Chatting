package kr.dagger.domain.usecase.sign

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.repository.AuthRepository

class SignUpEmailAndPasswordUseCase(
	private val repository: AuthRepository
) {
	suspend operator fun invoke(email: String, password: String, displayName: String): Flow<Response<UserInfo>> {
		return repository.createUser(email, password, displayName)
	}
}