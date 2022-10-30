package kr.dagger.rocketpunchpretask.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {
	lateinit var binding: T

	abstract fun initView(savedInstanceState: Bundle?)

	abstract fun subscribeObservers()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, layoutResId)

		initView(savedInstanceState)
		subscribeObservers()
	}
}