package kr.dagger.chat.presentation.ui.people

import androidx.recyclerview.widget.DiffUtil
import kr.dagger.domain.model.User

class PeopleDiffCallback : DiffUtil.ItemCallback<User>() {

	override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
		return oldItem.info == newItem.info
	}

	override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
		return oldItem == newItem
	}
}