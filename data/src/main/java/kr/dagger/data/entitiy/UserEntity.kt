package kr.dagger.data.entitiy

import com.google.firebase.database.PropertyName
import kr.dagger.domain.model.UserInfo

data class UserEntity (
	@get:PropertyName("info") @set:PropertyName("info") var info: UserInfoEntity = UserInfoEntity()
)