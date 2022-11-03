package kr.dagger.data.entity

import com.google.firebase.database.PropertyName
import kr.dagger.domain.model.Message

data class ChatEntity(
	@get:PropertyName("lastMessage") @set:PropertyName("lastMessage") var lastMessage: MessageEntity = MessageEntity(),
	@get:PropertyName("info") @set:PropertyName("info") var info: ChatInfoEntity = ChatInfoEntity()
)
