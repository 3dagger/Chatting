package kr.dagger.chat.presentation.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kr.dagger.chat.R
import kr.dagger.chat.presentation.ui.people.PeopleAdapter

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

	@JvmStatic
	@BindingAdapter("bindPeopleAdapter")
	fun RecyclerView.bindPeopleAdapter(adapter: PeopleAdapter) {
		if (this.adapter == null) {
			this.adapter = adapter
		}
	}

	@JvmStatic
	@BindingAdapter("bindSwipeRefresh")
	fun SwipeRefreshLayout.bindSwipeRefresh(listener: SwipeRefreshLayout.OnRefreshListener) {
		setOnRefreshListener(listener)
	}

}