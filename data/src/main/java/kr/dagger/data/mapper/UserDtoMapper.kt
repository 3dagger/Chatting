package kr.dagger.data.mapper

import kr.dagger.data.entitiy.UserEntity
import kr.dagger.domain.mapper.Mapper
import kr.dagger.domain.model.User
import kr.dagger.domain.model.UserInfo

class UserDtoMapper : Mapper<UserEntity, User> {

	override fun convert(fromObject: UserEntity): User {
		return User(
			info = UserInfo(
				id = fromObject.info.id,
				givenName = fromObject.info.givenName,
				displayName = fromObject.info.displayName,
				status = fromObject.info.status,
				profileImageUrl = fromObject.info.profileImageUrl
			)
		)
	}
}