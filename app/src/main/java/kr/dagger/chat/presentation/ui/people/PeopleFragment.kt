package kr.dagger.chat.presentation.ui.people

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentPeopleBinding

@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(R.layout.fragment_people) {

	private val viewModel: PeopleViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}
}