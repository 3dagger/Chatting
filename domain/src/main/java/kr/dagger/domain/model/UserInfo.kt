package kr.dagger.domain.model

data class UserInfo(
	var id: String = "",
	var givenName: String = "",
	var displayName: String = "",
	var status: String = "",
	var profileImageUrl: String = ""
)
