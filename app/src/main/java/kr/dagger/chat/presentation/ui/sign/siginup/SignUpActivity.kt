package kr.dagger.chat.presentation.ui.sign.siginup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivitySignUpBinding
import kr.dagger.chat.presentation.extension.showSnackBar
import kr.dagger.chat.presentation.ui.Constants.INTENT_SIGN_UP_RESULT
import kr.dagger.chat.presentation.ui.Constants.RESULT_CODE_SIGN_UP
import kr.dagger.chat.presentation.ui.sign.signin.SignInActivity

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

	private val viewModel: SignUpViewModel by viewModels()

	override fun initView(savedInstanceState: Bundle?) {
		binding.run {
			lifecycleOwner = this@SignUpActivity
			vm = viewModel
		}
	}

	override fun subscribeObservers() {
		viewModel.moveSignIn.observe(this) {
			val intent = Intent(this, SignInActivity::class.java).apply {
				putExtra(INTENT_SIGN_UP_RESULT, it)
			}
			setResult(RESULT_CODE_SIGN_UP, intent)
			finish()
		}

		viewModel.snackMessage.observe(this@SignUpActivity) { message ->
			showSnackBar(binding.root, message)
		}
	}
}