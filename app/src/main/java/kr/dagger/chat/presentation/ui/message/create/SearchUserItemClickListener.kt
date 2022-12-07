package kr.dagger.chat.presentation.ui.message.create

import kr.dagger.domain.model.User

interface SearchUserItemClickListener {

	fun itemClicked(user: User)
}