package kr.dagger.data.remote

import kotlinx.coroutines.flow.Flow
import kr.dagger.data.entity.ChatEntity
import kr.dagger.data.entity.UserEntity
import kr.dagger.data.entity.UserInfoEntity
import kr.dagger.domain.model.Chat
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.UserInfo


interface FirebaseDatabaseDataSource {

	suspend fun searchUsers(targetName: String): Flow<Response<List<UserEntity>>>

	suspend fun loadUsers(): Flow<Response<List<UserEntity>>>

	suspend fun loadChatList(): Flow<Response<List<ChatEntity>>>

	suspend fun loadChat(): Flow<Response<ChatEntity>>

	suspend fun loadUserInfo(userId: String): Flow<Response<UserInfoEntity>>

	fun updateNewUser(user: UserEntity)

	fun updateNewChat(chat: ChatEntity)
}