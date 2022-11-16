package kr.dagger.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.dagger.domain.model.Chat
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.model.UserInfo

interface FirebaseDatabaseRepository {

	suspend fun searchUsers(targetName: String): Flow<Response<List<User>>>

	suspend fun loadUsers(): Flow<Response<List<User>>>

	suspend fun loadChatList(): Flow<Response<List<Chat>>>

	suspend fun loadChat(): Flow<Response<Chat>>

	suspend fun loadUserInfo(userId: String): Flow<Response<UserInfo>>

	suspend fun updateNewUser(user: User): Flow<Response<Unit>>

	fun updateNewChat(chat: Chat)
}