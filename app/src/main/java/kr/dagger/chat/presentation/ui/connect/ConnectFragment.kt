package kr.dagger.chat.presentation.ui.connect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentConnectBinding
import kr.dagger.domain.model.User
import timber.log.Timber

@AndroidEntryPoint
class ConnectFragment : BaseFragment<FragmentConnectBinding>(R.layout.fragment_connect) {

	private val viewModel: ConnectViewModel by viewModels()

	private val user: User by lazy { requireArguments().get("user") as User }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.run {
			lifecycleOwner = viewLifecycleOwner
			fm = this@ConnectFragment
			vm = viewModel
			user = this@ConnectFragment.user
		}

	}
}