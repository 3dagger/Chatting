package kr.dagger.chat.presentation.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentChatBinding

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {

	private val viewModel: ChatViewModel by viewModels()

	private lateinit var listAdapter: ChatListAdapter
	private lateinit var listAdapterObserver: RecyclerView.AdapterDataObserver

	companion object {
		const val ARGUMENTS_KEY_USER_ID = "bundle_user_id"
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.run {
			lifecycleOwner = viewLifecycleOwner
		}

		setUpListAdapter()

	}

	private fun setUpListAdapter() {
		listAdapterObserver = object : RecyclerView.AdapterDataObserver() {
			override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
				binding.recyclerView.scrollToPosition(positionStart)
			}
		}
		listAdapter = ChatListAdapter(viewModel, requireArguments().getString(ARGUMENTS_KEY_USER_ID)!!)
		listAdapter.registerAdapterDataObserver(listAdapterObserver)
		binding.recyclerView.adapter = listAdapter
	}

	override fun onDestroyView() {
		super.onDestroyView()
		listAdapter.unregisterAdapterDataObserver(listAdapterObserver)
	}
}