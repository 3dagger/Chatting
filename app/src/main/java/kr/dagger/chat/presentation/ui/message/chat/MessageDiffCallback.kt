package kr.dagger.chat.presentation.ui.message.chat

import androidx.recyclerview.widget.DiffUtil
import kr.dagger.domain.model.Message

class MessageDiffCallback : DiffUtil.ItemCallback<Message>() {

	override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
		return oldItem == newItem
	}

	override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
		return oldItem.sentDate == newItem.sentDate
	}

}