package kr.dagger.data.remote

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kr.dagger.data.entity.ChatEntity
import kr.dagger.data.entity.UserEntity
import kr.dagger.data.entity.UserInfoEntity
import kr.dagger.domain.model.Response
import javax.inject.Inject

class FirebaseDatabaseDataSourceImpl @Inject constructor(
	private val database: FirebaseDatabase
) : FirebaseDatabaseDataSource {

	override suspend fun searchUsers(targetName: String): Flow<Response<List<UserEntity>>> = flow {
		try {
			emit(Response.Loading)

			database.reference.child("users").child(targetName).get().await().children.mapNotNull { doc ->
				doc.getValue(UserEntity::class.java)
			}.apply {
				emit(Response.Success(this))
			}

			val res = database.reference.child("users").child(targetName).get().await().children.mapNotNull { doc ->
				doc.getValue(UserEntity::class.java)
			}

			emit(Response.Success(res))
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

	override suspend fun loadUsers(myUid: String): Flow<Response<List<UserEntity>>> = flow {
		try {
			emit(Response.Loading)
			database.reference.child("users").get().await().children.mapNotNull { doc ->
				doc.getValue(UserEntity::class.java)
			}.run {
				emit(Response.Success(this.filterNot { it.info.id == myUid }))
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

	override suspend fun loadChatList(): Flow<Response<List<ChatEntity>>> = flow {
		try {
			emit(Response.Loading)
			database.reference.child("chats").get().await().children.mapNotNull { doc ->
				doc.getValue(ChatEntity::class.java)
			}.run {
				emit(Response.Success(this))
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

	override suspend fun loadChat(): Flow<Response<ChatEntity>> = flow {
		try {
			emit(Response.Loading)
			database.reference.child("chats").get().await().getValue(ChatEntity::class.java)!!.run {
				emit(Response.Success(this))
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

	override suspend fun loadUserInfo(userId: String): Flow<Response<UserInfoEntity>> = flow {
		try {
			emit(Response.Loading)
			database.reference.child("users/$userId/info").get().await().getValue(UserInfoEntity::class.java)!!.run {
				emit(Response.Success(this))
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

	override suspend fun updateNewUser(user: UserEntity) = callbackFlow {
			trySend(Response.Loading)
			database.reference
				.child("users/${user.info.id}")
				.setValue(user)
				.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						trySend(Response.Success(Unit))
					} else {
						trySend(Response.Error(task.exception?.message ?: ""))
					}
				}
			awaitClose { this.cancel() }
	}

	override fun updateNewChat(chat: ChatEntity) {
		database.reference.child("chats/${chat.info.id}").setValue(chat)
	}
}