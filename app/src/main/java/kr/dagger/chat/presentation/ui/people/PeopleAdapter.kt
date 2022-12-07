package kr.dagger.chat.presentation.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.dagger.chat.databinding.ItemSearchUserListBinding
import kr.dagger.chat.presentation.ui.message.create.SearchUserItemClickListener
import kr.dagger.domain.model.User

class PeopleAdapter(private val listener: SearchUserItemClickListener) : ListAdapter<User, PeopleAdapter.PeopleViewHolder>(PeopleDiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
		return PeopleViewHolder(ItemSearchUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	inner class PeopleViewHolder(private val binding: ItemSearchUserListBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: User) {
			binding.run {
				user = item
				listener = this@PeopleAdapter.listener
			}
		}
	}
}