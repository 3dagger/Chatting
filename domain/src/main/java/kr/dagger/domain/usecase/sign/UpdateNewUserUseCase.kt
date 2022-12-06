package kr.dagger.domain.usecase.sign

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class UpdateNewUserUseCase(
	private val repository: FirebaseDatabaseRepository
) {
	suspend fun invoke(user: User) : Flow<Response<Unit>> {
		return repository.updateNewUser(user)
	}
}