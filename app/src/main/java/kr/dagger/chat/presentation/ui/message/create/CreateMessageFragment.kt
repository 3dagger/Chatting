package kr.dagger.chat.presentation.ui.message.create

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.domain.model.Chat
import kr.dagger.domain.model.Message
import kr.dagger.domain.model.Response
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentCreateMessageBinding
import kr.dagger.chat.presentation.common.ButtonClickListener
import kr.dagger.chat.presentation.ui.message.chat.ChatFragment

@AndroidEntryPoint
class CreateMessageFragment : BaseFragment<FragmentCreateMessageBinding>(R.layout.fragment_create_message),
	ButtonClickListener, SearchUserItemClickListener {

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

		binding.recyclerView.adapter = this.adapter
		adapter.setSearchUserItemClickListener(this)

		viewModel.getLoadAllUser().observe(viewLifecycleOwner) {
			when (it) {
				is Response.Loading -> {
					binding.progressBar.visibility = View.VISIBLE
				}
				is Response.Success -> {
					binding.progressBar.visibility = View.INVISIBLE
					val submitListData = it.data.filter { it.info.id != viewModel.myUserId.value }
					adapter.submitList(submitListData)
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

	override fun itemClicked(userId: String) {
		val newChat = Chat().apply {
			info.id = viewModel.myUserId.value + userId
			lastMessage = Message(seen = true, text = "안녕하세요.", senderId = viewModel.myUserId.value!!)
		}
		viewModel.updateNewChat(newChat)

		val bundle = bundleOf(
			ChatFragment.ARGUMENTS_KEY_USER_ID to userId
		)
		findNavController().navigate(R.id.action_navigation_createMessageFragment_to_chatFragment, bundle)
	}
}