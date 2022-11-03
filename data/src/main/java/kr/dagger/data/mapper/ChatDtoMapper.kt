package kr.dagger.data.mapper

import kr.dagger.data.entity.ChatEntity
import kr.dagger.domain.mapper.Mapper
import kr.dagger.domain.model.Chat
import kr.dagger.domain.model.ChatInfo
import kr.dagger.domain.model.Message

class ChatDtoMapper : Mapper<ChatEntity, Chat> {

	override fun convert(fromObject: ChatEntity): Chat {
		return Chat(
			lastMessage = Message(
				senderId = fromObject.lastMessage.senderId,
				text = fromObject.lastMessage.text,
				sentDate = fromObject.lastMessage.sendDate,
				seen = fromObject.lastMessage.seen
			),
			info = ChatInfo(
				id = fromObject.info.id
			)
		)
	}
}