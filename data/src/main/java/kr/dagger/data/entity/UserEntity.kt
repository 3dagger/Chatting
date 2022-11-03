package kr.dagger.data.entity

import com.google.firebase.database.PropertyName

data class UserEntity (
	@get:PropertyName("info") @set:PropertyName("info") var info: UserInfoEntity = UserInfoEntity()
)