package kr.dagger.rocketpunchpretask.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.window.SplashScreen
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.rocketpunchpretask.R
import kr.dagger.rocketpunchpretask.base.BaseActivity
import kr.dagger.rocketpunchpretask.databinding.ActivitySplashBinding
import kr.dagger.rocketpunchpretask.presentation.extension.openActivity
import kr.dagger.rocketpunchpretask.presentation.ui.MainActivity
import kr.dagger.rocketpunchpretask.presentation.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

	private val viewModel: SplashViewModel by viewModels()

	override fun initView(savedInstanceState: Bundle?) {
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
		openActivity(LoginActivity::class.java)
		finish()
	}

	private fun moveMain() {
		openActivity(MainActivity::class.java)
		finish()
	}
}