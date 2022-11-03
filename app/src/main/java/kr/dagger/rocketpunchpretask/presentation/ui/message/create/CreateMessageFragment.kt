package kr.dagger.rocketpunchpretask.presentation.ui.message.create

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kr.dagger.domain.model.Response
import kr.dagger.rocketpunchpretask.R
import kr.dagger.rocketpunchpretask.base.BaseFragment
import kr.dagger.rocketpunchpretask.databinding.FragmentCreateMessageBinding
import kr.dagger.rocketpunchpretask.presentation.common.ButtonClickListener
import javax.inject.Inject

@AndroidEntryPoint
class CreateMessageFragment : BaseFragment<FragmentCreateMessageBinding>(R.layout.fragment_create_message), ButtonClickListener {

	private val adapter: SearchUserListAdapter by lazy { SearchUserListAdapter() }

	private val viewModel: CreateMessageViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.run {
			lifecycleOwner = this@CreateMessageFragment
			isHaveClose = true
			clickListener = this@CreateMessageFragment
			vm = viewModel
		}

		binding.recylcerView.adapter = this.adapter

		viewModel.getLoadAllUser().observe(viewLifecycleOwner) {
			when (it) {
				is Response.Loading -> {
					binding.progressBar.visibility = View.VISIBLE
				}
				is Response.Success -> {
					binding.progressBar.visibility = View.INVISIBLE
					adapter.submitList(it.data)
				}
				is Response.Error -> {
					binding.progressBar.visibility = View.INVISIBLE
				}
			}
		}

		viewModel.searchData.observe(viewLifecycleOwner) {
			if (it.length >= 2) {
				getSearchUsers(it)
			}
		}
	}

	private fun getSearchUsers(targetName: String) {
		viewModel.searchUsers(targetName).observe(viewLifecycleOwner) {
			when (it) {
				is Response.Loading -> {
					binding.progressBar.visibility = View.VISIBLE
				}
				is Response.Success -> {
					binding.progressBar.visibility = View.INVISIBLE
					adapter.submitList(it.data)
				}
				is Response.Error -> {
					binding.progressBar.visibility = View.INVISIBLE
				}
			}
		}
	}

	override fun onLeftClicked() {
		navigateToMessageFragment()
	}

	private fun navigateToMessageFragment() {
		findNavController().navigate(R.id.action_navigation_createMessageFragment_to_messageFragment)
	}
}