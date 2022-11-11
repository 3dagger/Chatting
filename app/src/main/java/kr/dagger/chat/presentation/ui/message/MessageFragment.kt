package kr.dagger.chat.presentation.ui.message

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.domain.model.Response
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentMessageBinding
import kr.dagger.chat.presentation.common.ButtonClickListener

@AndroidEntryPoint
class MessageFragment : BaseFragment<FragmentMessageBinding>(R.layout.fragment_message), ButtonClickListener {

	private val viewModel: MessageViewModel by viewModels()

	private lateinit var listAdapter: MessageListAdapter

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.run {
			lifecycleOwner = this@MessageFragment
			fm = this@MessageFragment
			clickListener = this@MessageFragment
		}

		viewModel.loadChat().observe(viewLifecycleOwner) {
			when (it) {
				is Response.Loading -> {
					binding.progressBar.visibility = View.VISIBLE
				}
				is Response.Success -> {
					binding.progressBar.visibility = View.INVISIBLE
				}
				is Response.Error -> {
					binding.progressBar.visibility = View.INVISIBLE
				}
			}
		}


		setUpListAdapter()
	}

	private fun setUpListAdapter() {
		listAdapter = MessageListAdapter()
		binding.recyclerView.adapter = listAdapter
	}

	override fun onRightClicked() {
		navigateToCreateMessageFragment()
	}

	private fun navigateToCreateMessageFragment() {
		findNavController().navigate(R.id.action_navigation_messageFragment_to_createMessageFragment)
	}
}