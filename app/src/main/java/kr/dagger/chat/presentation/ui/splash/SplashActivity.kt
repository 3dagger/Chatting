package kr.dagger.chat.presentation.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivitySplashBinding
import kr.dagger.chat.presentation.extension.openActivity
import kr.dagger.chat.presentation.ui.MainActivity
import kr.dagger.chat.presentation.ui.sign.signin.SignInActivity

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

	private val viewModel: SplashViewModel by viewModels()

	override fun initView(savedInstanceState: Bundle?) {
		subscribeObservers()
	}

	override fun subscribeObservers() {
		viewModel.isLoggedUser.observe(this@SplashActivity) {
			when (it) {
				true -> moveMain()
				false -> moveLogin()
			}
		}
	}

	private fun moveLogin() {
		openActivity(SignInActivity::class.java)
		finish()
	}

	private fun moveMain() {
		openActivity(MainActivity::class.java)
		finish()
	}
}