package kr.dagger.rocketpunchpretask.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.window.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.rocketpunchpretask.R
import kr.dagger.rocketpunchpretask.base.BaseActivity
import kr.dagger.rocketpunchpretask.databinding.ActivitySplashBinding
import kr.dagger.rocketpunchpretask.presentation.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

	override fun initView(savedInstanceState: Bundle?) {
		Handler(Looper.myLooper()!!).postDelayed({
			startActivity(Intent(this, LoginActivity::class.java))
		}, 1500)
		finish()
	}
	override fun subscribeObservers() {

	}

}