package kr.dagger.domain.usecase.auth

import kr.dagger.domain.model.Chat
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class UpdateNewChatUseCase(
	private val repository: FirebaseDatabaseRepository
) {

	fun updateNewChat(chat: Chat) {
		repository.updateNewChat(chat)
	}

}