package kr.dagger.chat.presentation.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kr.dagger.chat.R

object ViewBinding {

	@JvmStatic
	@BindingAdapter("bindImage")
	fun ImageView.bindImage(url: Uri?) {
		val options = RequestOptions()
			.placeholder(R.drawable.ic_rocket_punch_logo_background)
			.error(R.drawable.ic_rocket_punch_logo_background)

		url?.let {
			Glide.with(this.context).load(it).apply(options).into(this)
		} ?: run {
			Glide.with(this.context).load(R.drawable.rocket_punch_login).into(this)
		}
	}

	@JvmStatic
	@BindingAdapter("bindStringImage")
	fun ImageView.bindStringImage(url: String?) {
		val options = RequestOptions()
			.placeholder(R.drawable.ic_rocket_punch_logo_background)
			.error(R.drawable.ic_rocket_punch_logo_background)

		url?.let {
			Glide.with(this.context).load(it).apply(options).into(this)
		} ?: run {
			Glide.with(this.context).load(R.drawable.rocket_punch_login).into(this)
		}
	}

}