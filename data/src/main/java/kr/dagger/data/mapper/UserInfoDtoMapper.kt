package kr.dagger.data.mapper

import kr.dagger.data.entity.UserInfoEntity
import kr.dagger.domain.mapper.Mapper
import kr.dagger.domain.model.UserInfo

class UserInfoDtoMapper : Mapper<UserInfoEntity, UserInfo> {

	override fun convert(fromObject: UserInfoEntity): UserInfo {
		return UserInfo(
			id = fromObject.id,
			givenName = fromObject.givenName,
			displayName = fromObject.displayName,
			status = fromObject.displayName,
			profileImageUrl = fromObject.profileImageUrl
		)
	}
}