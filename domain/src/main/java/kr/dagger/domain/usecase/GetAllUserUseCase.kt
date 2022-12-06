package kr.dagger.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class GetAllUserUseCase(
	private val repository: FirebaseDatabaseRepository
) {
	suspend operator fun invoke() : Flow<Response<List<User>>> {
		return repository.loadUsers()
	}
}