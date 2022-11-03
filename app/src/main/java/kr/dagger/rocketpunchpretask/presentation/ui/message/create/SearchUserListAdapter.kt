package kr.dagger.rocketpunchpretask.presentation.ui.message.create

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.dagger.domain.model.User
import kr.dagger.rocketpunchpretask.databinding.ItemSearchUserListBinding

class SearchUserListAdapter : ListAdapter<User, SearchUserListAdapter.SearchUserListViewHolder>(SearchUserLitDiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserListViewHolder {
		return SearchUserListViewHolder(ItemSearchUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: SearchUserListViewHolder, position: Int) {
//		Log.d("leeam", "data :: ${currentList[position]}")
		return holder.bind(currentList[position])
	}

	inner class SearchUserListViewHolder(private val binding: ItemSearchUserListBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(data: User) {
			this.binding.user = data
		}
	}
}