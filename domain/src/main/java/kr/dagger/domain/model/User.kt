package kr.dagger.domain.model

import java.io.Serializable

data class User(
	val info: UserInfo = UserInfo()
) : Serializable
