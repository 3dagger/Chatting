package kr.dagger.chat.presentation.ui.message.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.dagger.domain.model.User
import kr.dagger.chat.databinding.ItemSearchUserListBinding

class SearchUserListAdapter : ListAdapter<User, SearchUserListAdapter.SearchUserListViewHolder>(SearchUserLitDiffCallback()) {

	private var listener: SearchUserItemClickListener? = null

	fun setSearchUserItemClickListener(listener: SearchUserItemClickListener) {
		this.listener = listener
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserListViewHolder {
		return SearchUserListViewHolder(ItemSearchUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: SearchUserListViewHolder, position: Int) {
		return holder.bind(currentList[position])
	}

	inner class SearchUserListViewHolder(private val binding: ItemSearchUserListBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(data: User) {
			binding.apply {
				user = data
				listener = this@SearchUserListAdapter.listener
			}
		}
	}
}