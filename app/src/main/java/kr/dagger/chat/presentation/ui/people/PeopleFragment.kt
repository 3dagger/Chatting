package kr.dagger.chat.presentation.ui.people

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentPeopleBinding
import kr.dagger.chat.presentation.extension.showSnackBar
import kr.dagger.chat.presentation.ui.message.create.SearchUserItemClickListener
import kr.dagger.domain.model.User

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people), SearchUserItemClickListener {

	private val viewModel: PeopleViewModel by viewModels()

	val peopleAdapter: PeopleAdapter by lazy { PeopleAdapter(this) }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.run {
			lifecycleOwner = viewLifecycleOwner
			fragment = this@PeopleFragment
			vm = viewModel
		}

		viewModel.userList.observe(viewLifecycleOwner) {
			peopleAdapter.submitList(it)
		}

		viewModel.snackMessage.observe(viewLifecycleOwner) {
			showSnackBar(view, it)
		}
	}

	override fun itemClicked(user: User) {
		val bundle = bundleOf("user" to user)
		findNavController().navigate(R.id.action_navigation_peopleFragment_to_connectFragment, bundle)
	}
}