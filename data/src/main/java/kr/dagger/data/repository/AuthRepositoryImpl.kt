package kr.dagger.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kr.dagger.domain.model.Response
import kr.dagger.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val googleSignInClient: GoogleSignInClient,
	private val auth: FirebaseAuth
) : AuthRepository {

	override fun isLoggedInUser(): Boolean {
		return auth.currentUser != null
	}

	override suspend fun loginUser(idToken: String) = flow {
		try {
			emit(Response.Loading)
			val credential = GoogleAuthProvider.getCredential(idToken, null)
			auth.signInWithCredential(credential).await().additionalUserInfo?.apply {
				emit(Response.Success(isNewUser))
			}
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

	override suspend fun logoutUser(): Flow<Response<Void>> = flow {
		try {
			emit(Response.Loading)
			googleSignInClient.signOut().await().run {
				emit(Response.Success(this))
			}
			auth.signOut()
		} catch (e: Exception) {
			emit(Response.Error(e.message ?: "요청에 실패하였습니다."))
		}
	}

}