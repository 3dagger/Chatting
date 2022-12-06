package kr.dagger.domain.usecase.sign

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.repository.AuthRepository

class SignInGoogleUseCase(
	private val repository: AuthRepository
) {
	suspend operator fun invoke(idToken: String) : Flow<Response<UserInfo>> {
		return repository.signInGoogle(idToken)
	}
}