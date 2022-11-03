package kr.dagger.data.entity

import com.google.firebase.database.PropertyName

data class ChatInfoEntity(
	@get:PropertyName("id") @set:PropertyName("id") var id: String = ""
)
