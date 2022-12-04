package kr.dagger.chat.presentation.ui.sign.siginup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivitySignUpBinding
import kr.dagger.chat.presentation.extension.toast
import kr.dagger.chat.presentation.ui.Constants
import kr.dagger.chat.presentation.ui.Constants.RESULT_CODE_SIGN_UP
import kr.dagger.chat.presentation.ui.sign.signin.SignInActivity
import timber.log.Timber

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

	private val viewModel: SignUpViewModel by viewModels()

	override fun initView(savedInstanceState: Bundle?) {
		binding.run {
			lifecycleOwner = this@SignUpActivity
			vm = viewModel
		}

		viewModel.currentPasswordText.observe(this) {
			Timber.d("currentPasswordText :: $it")
		}

		viewModel.currentEmailText.observe(this) {
			Timber.d("currentEmailText :: $it")
		}

		viewModel.moveSignIn.observe(this) {
			val intent = Intent(this, SignInActivity::class.java)
			intent.putExtra("AA", it)
			setResult(RESULT_CODE_SIGN_UP, intent)
			finish()
//			Timber.d("hashmap :: $it")
		}

		viewModel.toastMessage.observe(this) { errorMessage ->
			toast(errorMessage)
		}
	}

	override fun subscribeObservers() {

	}
}