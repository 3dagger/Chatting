package kr.dagger.domain.model

import java.util.*

data class Message(
	var senderId: String = "",
	var text: String = "",
	var sentDate: Long = Date().time,
	var seen: Boolean = false
)
