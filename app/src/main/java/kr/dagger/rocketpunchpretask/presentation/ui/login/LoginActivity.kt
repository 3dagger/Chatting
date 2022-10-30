package kr.dagger.rocketpunchpretask.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.rocketpunchpretask.MainActivity
import kr.dagger.rocketpunchpretask.R
import kr.dagger.rocketpunchpretask.base.BaseActivity
import kr.dagger.rocketpunchpretask.databinding.ActivityLoginBinding

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

	private val viewModel: LoginViewModel by viewModels()
//	private lateinit var firebase : Firebase
	private var auth = FirebaseAuth.getInstance()

	private lateinit var signIn: ActivityResultLauncher<Intent>

	override fun initView(savedInstanceState: Bundle?) {
		binding.run {
			lifecycleOwner = this@LoginActivity
			activity = this@LoginActivity
			vm = viewModel
		}

		signIn = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
			if (it.resultCode == RESULT_OK) {
				val result = Auth.GoogleSignInApi.getSignInResultFromIntent(it.data!!)
				Log.d("leeam", "result :: ${result?.signInAccount?.id}")
				sendGoogleAccountInfoToFirebase(result?.signInAccount)
			}
		}
	}

	override fun subscribeObservers() {
	}

	private fun sendGoogleAccountInfoToFirebase(account: GoogleSignInAccount?) {
		val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
		auth.signInWithCredential(credential)
			.addOnCompleteListener {
				Log.d("leeam", "result2 :: ${it}")
				if (it.isSuccessful) {
					moveMainActivity()
				}
			}
	}

	fun signIn() {
		val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestIdToken(getString(R.string.default_web_client_id))
			.requestEmail()
			.build()
		val googleSignInClient = GoogleSignIn.getClient(this, gso)
		val signInIntent = googleSignInClient.signInIntent
		signIn.launch(signInIntent)
	}

	fun moveMainActivity() {
		startActivity(Intent(this, MainActivity::class.java))
		finish()
	}
}