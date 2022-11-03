package kr.dagger.data.mapper

import kr.dagger.data.entity.ChatEntity
import kr.dagger.data.entity.ChatInfoEntity
import kr.dagger.data.entity.MessageEntity
import kr.dagger.domain.mapper.Mapper
import kr.dagger.domain.model.Chat

class ChatModelToChatEntityMapper : Mapper<Chat, ChatEntity> {

	override fun convert(fromObject: Chat): ChatEntity {
		return ChatEntity(
			lastMessage = MessageEntity(
				senderId = fromObject.lastMessage.senderId,
				text = fromObject.lastMessage.text,
				sendDate = fromObject.lastMessage.sentDate,
				seen = fromObject.lastMessage.seen
			) ,
			info = ChatInfoEntity(
				id = fromObject.info.id
			)
		)
	}
}