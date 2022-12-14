package kr.dagger.chat.di

import android.app.Application
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.dagger.chat.R

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	fun provideGso(app: Application): GoogleSignInOptions {
		return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestIdToken(app.getString(R.string.default_web_client_id))
			.requestEmail()
			.requestProfile()
			.build()
	}

	@Provides
	fun provideGoogleSignInClient(
		gso: GoogleSignInOptions,
		app: Application
	) : GoogleSignInClient {
		return GoogleSignIn.getClient(app, gso)
	}

	@Provides
	fun provideSignInIntent(googleSignInClient: GoogleSignInClient): Intent {
		return googleSignInClient.signInIntent
	}

}