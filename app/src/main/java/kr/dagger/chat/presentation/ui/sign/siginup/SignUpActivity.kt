package kr.dagger.chat.presentation.ui.sign.siginup

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivitySignUpBinding
import kr.dagger.chat.presentation.extension.toast
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

		viewModel.toastMessage.observe(this) { errorMessage ->
			toast(errorMessage)
		}
	}
}