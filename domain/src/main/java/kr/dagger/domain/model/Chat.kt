package kr.dagger.domain.model

data class Chat(
	var lastMessage: Message = Message(),
	var info: ChatInfo = ChatInfo()
)
