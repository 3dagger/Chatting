package kr.dagger.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Chat
import kr.dagger.domain.model.Response
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class GetChatUseCase(
	private val repository: FirebaseDatabaseRepository
) {
	suspend operator fun invoke(): Flow<Response<Chat>> {
		return repository.loadChat()
	}
}