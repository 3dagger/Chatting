package kr.dagger.rocketpunchpretask.presentation.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.rocketpunchpretask.R
import kr.dagger.rocketpunchpretask.base.BaseActivity
import kr.dagger.rocketpunchpretask.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

	private lateinit var navController: NavController

	override fun initView(savedInstanceState: Bundle?) {
		initNavHostFragment()
		initBottomNav()
	}

	private fun initNavHostFragment() {
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
		navController = navHostFragment.findNavController()
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
}