package kr.dagger.data.remote

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kr.dagger.data.entitiy.UserEntity
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
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

	override suspend fun loadUsers(): Flow<Response<List<UserEntity>>> = flow {
		try {
			emit(Response.Loading)
			database.reference.child("users").get().await().children.mapNotNull { doc ->
				doc.getValue(UserEntity::class.java)
			}.run {
				emit(Response.Success(this))
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

	override fun updateNewUser(user: UserEntity) {
		database.reference.child("users/${user.info.displayName}").setValue(user)
	}
}