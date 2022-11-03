package kr.dagger.rocketpunchpretask.presentation.ui.message.create

import androidx.recyclerview.widget.DiffUtil
import kr.dagger.domain.model.User

class SearchUserLitDiffCallback : DiffUtil.ItemCallback<User>() {

	override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
		return oldItem == newItem
	}

	override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
		return oldItem == newItem
	}

}