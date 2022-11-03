package kr.dagger.data.entity

import com.google.firebase.database.PropertyName
import java.util.*

data class MessageEntity(
	@get:PropertyName("senderId") @set:PropertyName("senderId") var senderId: String = "",
	@get:PropertyName("text") @set:PropertyName("text") var text: String = "",
	@get:PropertyName("sendDate") @set:PropertyName("sendDate") var sendDate: Long = Date().time,
	@get:PropertyName("seen") @set:PropertyName("seen") var seen: Boolean = false
)
