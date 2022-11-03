package kr.dagger.data.entity

import com.google.firebase.database.PropertyName

data class UserInfoEntity (
	@get:PropertyName("id") @set:PropertyName("id") var id: String = "",
	@get:PropertyName("displayName") @set:PropertyName("displayName") var displayName: String = "",
	@get:PropertyName("givenName") @set:PropertyName("givenName") var givenName: String = "",
	@get:PropertyName("status") @set:PropertyName("status") var status: String = "",
	@get:PropertyName("profileImageUrl") @set:PropertyName("profileImageUrl") var profileImageUrl: String = ""
)
