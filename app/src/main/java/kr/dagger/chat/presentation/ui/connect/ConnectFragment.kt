package kr.dagger.chat.presentation.ui.connect

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentConnectBinding
import timber.log.Timber

@AndroidEntryPoint
class ConnectFragment : BaseFragment<FragmentConnectBinding>(R.layout.fragment_connect) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val user = requireArguments().get("user")
		Timber.d("user :: $user")
	}
}