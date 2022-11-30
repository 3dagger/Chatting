package kr.dagger.chat.presentation.ui.sign.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.Auth
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivitySignInBinding
import kr.dagger.chat.presentation.extension.openActivity
import kr.dagger.chat.presentation.extension.toast
import kr.dagger.chat.presentation.ui.MainActivity
import kr.dagger.chat.presentation.ui.sign.siginup.SignUpActivity
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

	@Inject
	lateinit var signInIntent: Intent

	lateinit var signIn: ActivityResultLauncher<Intent>

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
				viewModel.googleSignIn2(result?.signInAccount!!)
			} else {
				toast(message = "로그인에 실패하였습니다.")
			}
		}
	}

	override fun subscribeObservers() {
		viewModel.moveMain.observe(this) {
			openActivity(MainActivity::class.java)
			finish()
		}
	}

	fun signUp() {
		openActivity(SignUpActivity::class.java)
	}
}