package kr.dagger.data.mapper

import kr.dagger.data.entitiy.UserEntity
import kr.dagger.data.entitiy.UserInfoEntity
import kr.dagger.domain.mapper.Mapper
import kr.dagger.domain.model.User

class UserModelToUserEntityMapper : Mapper<User, UserEntity> {

	override fun convert(fromObject: User): UserEntity {
		return UserEntity(
			info = UserInfoEntity(
				id = fromObject.info.id,
				givenName = fromObject.info.givenName,
				displayName = fromObject.info.displayName,
				status = fromObject.info.status,
				profileImageUrl = fromObject.info.profileImageUrl
			)
		)
	}
}