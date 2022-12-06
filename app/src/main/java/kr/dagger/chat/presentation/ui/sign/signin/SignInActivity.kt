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
import kr.dagger.chat.presentation.extension.showSnackBar
import kr.dagger.chat.presentation.ui.Constants.INTENT_SIGN_UP_RESULT
import kr.dagger.chat.presentation.ui.Constants.RESULT_CODE_SIGN_UP
import kr.dagger.chat.presentation.ui.MainActivity
import kr.dagger.chat.presentation.ui.sign.SignRequired
import kr.dagger.chat.presentation.ui.sign.siginup.SignUpActivity
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

	@Inject
	lateinit var googleSignInIntent: Intent

	lateinit var signIn: ActivityResultLauncher<Intent>

	val signUpIntent: Intent by lazy { Intent(this, SignUpActivity::class.java) }

	private val viewModel: SignInViewModel by viewModels()

	override fun initView(savedInstanceState: Bundle?) {
		binding.run {
			lifecycleOwner = this@SignInActivity
			activity = this@SignInActivity
			vm = viewModel
		}

		signIn = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
			when (it.resultCode) {
				RESULT_OK -> {
					val result = Auth.GoogleSignInApi.getSignInResultFromIntent(it.data!!)
					viewModel.googleSignIn(result?.signInAccount!!)
				}
				RESULT_CODE_SIGN_UP -> {
					val result: HashMap<SignRequired, String> = it.data?.getSerializableExtra(INTENT_SIGN_UP_RESULT) as HashMap<SignRequired, String>
					viewModel.currentEmailText.value = result[SignRequired.Email]
					viewModel.currentPasswordText.value = result[SignRequired.Password]
				}
			}
		}
	}

	override fun subscribeObservers() {
		viewModel.snackMessage.observe(this) {
			showSnackBar(binding.root, it)
		}

		viewModel.moveMain.observe(this) {
			openActivity(MainActivity::class.java)
			finish()
		}
	}
}