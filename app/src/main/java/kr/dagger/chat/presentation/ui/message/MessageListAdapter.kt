package kr.dagger.chat.presentation.ui.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.dagger.domain.model.ChatWithUserInfo
import kr.dagger.chat.databinding.ItemMessageListBinding

class MessageListAdapter : ListAdapter<ChatWithUserInfo, MessageListAdapter.MessageListViewHolder>(MessageListDiffCallback()){

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
		return MessageListViewHolder(ItemMessageListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	inner class MessageListViewHolder(private val binding: ItemMessageListBinding): RecyclerView.ViewHolder(binding.root) {
		fun bind(item: ChatWithUserInfo) {
			binding.apply {
				data = item
			}
		}
	}
}