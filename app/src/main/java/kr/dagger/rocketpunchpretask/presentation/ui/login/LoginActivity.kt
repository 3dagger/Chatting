package kr.dagger.rocketpunchpretask.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.Auth
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.domain.model.Response
import kr.dagger.domain.model.User
import kr.dagger.domain.model.UserInfo
import kr.dagger.rocketpunchpretask.presentation.ui.MainActivity
import kr.dagger.rocketpunchpretask.R
import kr.dagger.rocketpunchpretask.base.BaseActivity
import kr.dagger.rocketpunchpretask.databinding.ActivityLoginBinding
import kr.dagger.rocketpunchpretask.presentation.extension.toast
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

	@Inject
	lateinit var signInIntent: Intent

	private lateinit var signIn: ActivityResultLauncher<Intent>

	private val viewModel: LoginViewModel by viewModels()

	override fun initView(savedInstanceState: Bundle?) {
		binding.run {
			lifecycleOwner = this@LoginActivity
			activity = this@LoginActivity
			vm = viewModel
		}

		signIn = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
			if (it.resultCode == RESULT_OK) {
				val result = Auth.GoogleSignInApi.getSignInResultFromIntent(it.data!!)
				result?.signInAccount?.idToken?.let { idToken ->
					signInWithGoogle(idToken)
					viewModel.saveMyUserId(result.signInAccount?.id ?: "")
					viewModel.updateNewUser(
						user = User(
							info = UserInfo(
								id = result.signInAccount?.id ?: "",
								givenName = result.signInAccount?.givenName ?: "",
								displayName = result.signInAccount?.displayName ?: "",
								status = "NewBie",
								profileImageUrl = result.signInAccount?.photoUrl.toString()
							)
						)
					)
				}
			} else {
				toast(message = "로그인에 실패하였습니다.")
			}
		}
	}

	private fun signInWithGoogle(idToken: String) {
		viewModel.googleSignIn(idToken).observe(this) {
			when (it) {
				is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
				is Response.Success -> {
					binding.progressBar.visibility = View.INVISIBLE
					moveMainActivity()
				}
				is Response.Error -> {
					binding.progressBar.visibility = View.INVISIBLE
					toast(it.errorMessage)
				}
			}
		}
	}

	override fun subscribeObservers() {
	}

	fun signIn() {
		signIn.launch(signInIntent)
	}

	/**
	 * @author : 이수현
	 * @Date : 2022/10/31 1:03 오전
	 * @Description : 메인 화면 이동 메서드
	 * @History :
	 *
	 **/
	private fun moveMainActivity() {
		startActivity(Intent(this, MainActivity::class.java))
		finish()
	}
}