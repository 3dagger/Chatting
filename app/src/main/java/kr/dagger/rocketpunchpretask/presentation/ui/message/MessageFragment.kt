package kr.dagger.rocketpunchpretask.presentation.ui.message

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kr.dagger.rocketpunchpretask.R
import kr.dagger.rocketpunchpretask.base.BaseFragment
import kr.dagger.rocketpunchpretask.databinding.FragmentMessageBinding
import kr.dagger.rocketpunchpretask.presentation.common.ButtonClickListener
import javax.inject.Inject

@AndroidEntryPoint
class MessageFragment : BaseFragment<FragmentMessageBinding>(R.layout.fragment_message), ButtonClickListener {

	private val viewModel: MessageViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.run {
			lifecycleOwner = this@MessageFragment
			fm = this@MessageFragment
			clickListener = this@MessageFragment
		}
	}

	override fun onRightClicked() {
		navigateToCreateMessageFragment()
	}

	private fun navigateToCreateMessageFragment() {
		findNavController().navigate(R.id.action_navigation_messageFragment_to_createMessageFragment)
	}
}