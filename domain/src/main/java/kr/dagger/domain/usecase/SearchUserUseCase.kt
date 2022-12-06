package kr.dagger.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class SearchUserUseCase(
	private val repository: FirebaseDatabaseRepository
) {
	suspend operator fun invoke(targetName: String): Flow<Response<List<User>>> {
		return repository.searchUsers(targetName)
	}
}