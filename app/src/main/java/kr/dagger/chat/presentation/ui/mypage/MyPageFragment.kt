package kr.dagger.chat.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.domain.model.Response
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentMypageBinding
import kr.dagger.chat.presentation.extension.openActivity
import kr.dagger.chat.presentation.extension.toast
import kr.dagger.chat.presentation.ui.login.LoginActivity

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage){

	private val viewModel: MyPageViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.run {
			lifecycleOwner = this@MyPageFragment
			fm = this@MyPageFragment
			profileImageUrl = FirebaseAuth.getInstance().currentUser?.photoUrl
			userName = FirebaseAuth.getInstance().currentUser?.displayName
		}
	}

	fun signOut() {
		viewModel.signOut().observe(this) { response ->
			when (response) {
				is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
				is Response.Success -> {
					binding.progressBar.visibility = View.INVISIBLE
					moveLoginActivity()
				}
				is Response.Error -> {
					binding.progressBar.visibility = View.INVISIBLE
					requireActivity().toast(response.errorMessage)
				}
			}
		}
	}

	private fun moveLoginActivity() {
		requireActivity().run {
			openActivity(LoginActivity::class.java)
			finish()
		}
	}
}