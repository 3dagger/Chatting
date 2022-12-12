package kr.dagger.chat.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseActivity
import kr.dagger.chat.databinding.ActivityMainBinding
import kr.dagger.chat.presentation.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

	private lateinit var navController: NavController

	private val viewModel: MainViewModel by viewModels()

	@Inject
	lateinit var firebaseAuth: FirebaseAuth

	override fun initView(savedInstanceState: Bundle?) {

		binding.run {
			lifecycleOwner = this@MainActivity
			activity = this@MainActivity
		}

		initNavHostFragment()
		initBottomNav()
		viewModel.saveUid()
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