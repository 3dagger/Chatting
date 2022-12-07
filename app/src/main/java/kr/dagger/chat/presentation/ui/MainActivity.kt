package kr.dagger.chat.presentation.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

	private lateinit var navController: NavController

	override fun initView(savedInstanceState: Bundle?) {

		binding.run {
			lifecycleOwner = this@MainActivity
			activity = this@MainActivity
		}

		initNavHostFragment()
		initBottomNav()

//		setSupportActionBar(binding.toolbar)
//		supportActionBar?.run {
//			setDisplayShowTitleEnabled(false)
//			setDisplayHomeAsUpEnabled(true)
//			setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
//		}
	}

	private fun initNavHostFragment() {
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
		navController = navHostFragment.findNavController()

		val appBarConfiguration = AppBarConfiguration(navController.graph)
		binding.toolbar.setupWithNavController(navController, appBarConfiguration)
	}

	private fun initBottomNav() {
		binding.bottomNav.apply {
			setupWithNavController(navController)
			setOnItemSelectedListener { item ->
				navController.navigate(item.itemId)
				true
			}
		}
	}

	override fun subscribeObservers() {

	}
}