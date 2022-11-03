package kr.dagger.rocketpunchpretask.presentation.ui.message

import androidx.recyclerview.widget.DiffUtil
import kr.dagger.domain.model.ChatWithUserInfo

class MessageListDiffCallback : DiffUtil.ItemCallback<ChatWithUserInfo>() {

	override fun areItemsTheSame(oldItem: ChatWithUserInfo, newItem: ChatWithUserInfo): Boolean {
		return oldItem == newItem
	}

	override fun areContentsTheSame(oldItem: ChatWithUserInfo, newItem: ChatWithUserInfo): Boolean {
		return oldItem.chat.info.id == newItem.chat.info.id
	}

}