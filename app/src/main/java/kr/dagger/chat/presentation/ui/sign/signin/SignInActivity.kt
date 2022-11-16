package kr.dagger.chat.presentation.ui.sign.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivitySignInBinding
import kr.dagger.chat.presentation.extension.inVisible
import kr.dagger.chat.presentation.extension.show
import kr.dagger.chat.presentation.extension.toast
import kr.dagger.chat.presentation.ui.MainActivity
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.model.UserInfo
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

	@Inject
	lateinit var signInIntent: Intent

	private lateinit var signIn: ActivityResultLauncher<Intent>

	private val viewModel: SignInViewModel by viewModels()

	override fun initView(savedInstanceState: Bundle?) {
		binding.run {
			lifecycleOwner = this@SignInActivity
			activity = this@SignInActivity
			vm = viewModel
		}

		signIn = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
			if (it.resultCode == RESULT_OK) {
				val result = Auth.GoogleSignInApi.getSignInResultFromIntent(it.data!!)
				viewModel.saveMyUserId(result?.signInAccount?.id ?: "")
				signInWithGoogle(result?.signInAccount!!)
			} else {
				toast(message = "로그인에 실패하였습니다.")
			}
		}
	}

	private fun updateNewUser(signInAccount: GoogleSignInAccount) {
		viewModel.updateNewUser(
			user = User(
				info = UserInfo(
					id = signInAccount.id ?: "",
					givenName = signInAccount.givenName ?: "",
					displayName = signInAccount.displayName ?: "",
					status = "NewBie",
					profileImageUrl = signInAccount.photoUrl.toString()
				)
			)
		).observe(this) {
			when (it) {
				is Response.Loading -> showProgress()
				is Response.Success<*> -> {
					dismissProgress()
					moveMainActivity()
				}
				is Response.Error -> {
					dismissProgress()
					toast(it.errorMessage)
				}
			}
		}
	}

	private fun signInWithGoogle(signInAccount: GoogleSignInAccount) {
		viewModel.googleSignIn(signInAccount.idToken ?: "").observe(this) {
			when (it) {
				is Response.Loading -> binding.progressBar.show()
				is Response.Success -> {
					binding.progressBar.inVisible()
					updateNewUser(signInAccount)
				}
				is Response.Error -> {
					binding.progressBar.inVisible()
					toast(it.errorMessage)
				}
			}
		}
	}

	fun signIn() {
		signIn.launch(signInIntent)
	}

	private fun moveMainActivity() {
		startActivity(Intent(this, MainActivity::class.java))
		finish()
	}

	private fun showProgress() {
		if (!binding.progressBar.isVisible) binding.progressBar.show()
	}

	private fun dismissProgress() {
		if (binding.progressBar.isVisible) binding.progressBar.inVisible()
	}
}