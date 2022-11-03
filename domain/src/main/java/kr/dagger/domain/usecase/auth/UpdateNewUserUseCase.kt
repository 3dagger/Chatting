package kr.dagger.domain.usecase.auth

import kr.dagger.domain.model.User
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class UpdateNewUserUseCase(
	private val repository: FirebaseDatabaseRepository
) {
	fun updateNewUser(user: User) {
		repository.updateNewUser(user)
	}
}