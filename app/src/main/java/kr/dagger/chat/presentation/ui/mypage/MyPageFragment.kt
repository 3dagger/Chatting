package kr.dagger.chat.presentation.ui.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.chat.R
import kr.dagger.chat.base.BaseFragment
import kr.dagger.chat.databinding.FragmentMypageBinding
import kr.dagger.chat.presentation.extension.openActivity
import kr.dagger.chat.presentation.extension.showSnackBar
import kr.dagger.chat.presentation.ui.sign.signin.SignInActivity

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

	private val viewModel: MyPageViewModel by viewModels()

	lateinit var resultLauncher: ActivityResultLauncher<Intent>

	val selectImageIntent: Intent by lazy { Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" } }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.run {
			lifecycleOwner = viewLifecycleOwner
			fm = this@MyPageFragment
			vm = viewModel
		}

		viewModel.getUserInfo()

		resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			when (result.resultCode) {
				RESULT_OK -> {
					result.data?.let { uri ->
						uri.data?.let { viewModel.changeProfileUri(it) }
					}
				}
			}
		}

		viewModel.snackMessage.observe(viewLifecycleOwner) { message ->
			showSnackBar(requireView(), message)
		}

		viewModel.moveSignIn.observe(viewLifecycleOwner) {
			requireActivity().run {
				openActivity(SignInActivity::class.java)
				finish()
			}
		}
	}
}