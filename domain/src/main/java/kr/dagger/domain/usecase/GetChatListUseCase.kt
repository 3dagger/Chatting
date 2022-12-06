package kr.dagger.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Chat
import kr.dagger.domain.model.Response
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class GetChatListUseCase(
	private val repository: FirebaseDatabaseRepository
) {
	suspend operator fun invoke(): Flow<Response<List<Chat>>> {
		return repository.loadChatList()
	}
}