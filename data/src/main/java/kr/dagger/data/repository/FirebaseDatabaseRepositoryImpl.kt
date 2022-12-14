package kr.dagger.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kr.dagger.data.mapper.*
import kr.dagger.data.remote.FirebaseDatabaseDataSource
import kr.dagger.domain.model.Chat
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.model.UserInfo
import kr.dagger.domain.repository.FirebaseDatabaseRepository

class FirebaseDatabaseRepositoryImpl(
	private val firebaseDatabaseDataSource: FirebaseDatabaseDataSource
) : FirebaseDatabaseRepository {

	override suspend fun searchUsers(targetName: String): Flow<Response<List<User>>> {
		return firebaseDatabaseDataSource.searchUsers(targetName).transform { response ->
			when (response) {
				is Response.Loading -> {
					emit(Response.Loading)
				}
				is Response.Error -> {
					emit(Response.Error(response.errorMessage))
				}
				is Response.Success -> {
					emit(Response.Success(data = response.data.map { UserDtoMapper().convert(it) }))
				}
			}
		}
	}

	override suspend fun loadUsers(myUid: String): Flow<Response<List<User>>> {
		return firebaseDatabaseDataSource.loadUsers(myUid).transform { response ->
			when (response) {
				is Response.Loading -> {
					emit(Response.Loading)
				}
				is Response.Error -> {
					emit(Response.Error(response.errorMessage))
				}
				is Response.Success -> {
					emit(Response.Success(data = response.data.map { UserDtoMapper().convert(it) }))
				}
			}
		}
	}

	override suspend fun loadChatList(): Flow<Response<List<Chat>>> {
		return firebaseDatabaseDataSource.loadChatList().transform { response ->
			when (response) {
				is Response.Loading -> {
					emit(Response.Loading)
				}
				is Response.Error -> {
					emit(Response.Error(response.errorMessage))
				}
				is Response.Success -> {
					emit(Response.Success(data = response.data.map { ChatDtoMapper().convert(it) }))
				}
			}
		}
	}

	override suspend fun loadChat(): Flow<Response<Chat>> {
		return firebaseDatabaseDataSource.loadChat().transform { response ->
			when (response) {
				is Response.Loading -> {
					emit(Response.Loading)
				}
				is Response.Error -> {
					emit(Response.Error(response.errorMessage))
				}
				is Response.Success -> {
					emit(Response.Success(data = ChatDtoMapper().convert(response.data)))
				}
			}
		}
	}

	override suspend fun loadUserInfo(userId: String): Flow<Response<UserInfo>> {
		return firebaseDatabaseDataSource.loadUserInfo(userId).transform { response ->
			when (response) {
				is Response.Loading -> {
					emit(Response.Loading)
				}
				is Response.Error -> {
					emit(Response.Error(response.errorMessage))
				}
				is Response.Success -> {
					emit(Response.Success(data = UserInfoDtoMapper().convert(response.data)))
				}
			}
		}
	}

	override suspend fun updateNewUser(user: User): Flow<Response<Unit>> {
		return firebaseDatabaseDataSource.updateNewUser(UserModelToUserEntityMapper().convert(user))
	}

	override fun updateNewChat(chat: Chat) {
		firebaseDatabaseDataSource.updateNewChat(ChatModelToChatEntityMapper().convert(chat))
	}
}