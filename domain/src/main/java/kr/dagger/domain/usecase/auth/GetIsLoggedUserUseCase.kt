package kr.dagger.domain.usecase.auth

import kr.dagger.domain.repository.AuthRepository

class GetIsLoggedUserUseCase(
	private val repository: AuthRepository
) {

	operator fun invoke(): Boolean {
		return repository.isLoggedInUser()
	}

}