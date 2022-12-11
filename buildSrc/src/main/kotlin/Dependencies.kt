object Dependencies {
	object Kotlin {
		const val StdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Essential.KotlinGradleVersion}"
	}

	object Coroutines {
		const val Core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.CoroutineVersion}"
		const val Android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Essential.CoroutineVersion}"
	}

	object AndroidSupport {
		const val CoreKtx = "androidx.core:core-ktx:${Versions.AndroidSupport.CoreKtxVersion}"
		const val AppCompat = "androidx.appcompat:appcompat:${Versions.AndroidSupport.AppCompatVersion}"
		const val Material = "com.google.android.material:material:${Versions.AndroidSupport.MaterialVersion}"
		const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidSupport.ConstraintLayoutVersion}"
		const val CardView = "androidx.cardview:cardview:${Versions.AndroidSupport.CardViewVersion}"
		const val ViewPager2 = "androidx.viewpager2:viewpager2:${Versions.AndroidSupport.ViewPager2Version}"
		const val RecyclerView = "androidx.recyclerview:recyclerview:${Versions.AndroidSupport.RecyclerViewVersion}"
		const val ActivityKtx = "androidx.activity:activity-ktx:${Versions.AndroidSupport.ActivityKtxVersion}"
		const val FragmentKtx = "androidx.fragment:fragment-ktx:${Versions.AndroidSupport.FragmentKtxVersion}"
		const val SwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidSupport.SwipeRefreshLayoutVersion}"
		const val Paging3 = "androidx.paging:paging-runtime:${Versions.AndroidSupport.Paging3Version}"

		object Navigation {
			const val FragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidSupport.NavigationVersion}"
			const val UiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidSupport.NavigationVersion}"
		}

		object LifeCycle {
			const val ViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidSupport.LifeCycleVersion}"
			const val LiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidSupport.LifeCycleVersion}"
			const val Runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidSupport.LifeCycleVersion}"
		}
	}

	object Hilt {
		const val Android = "com.google.dagger:hilt-android:${Versions.Jetpack.HiltVersion}"
		const val Compiler = "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HiltVersion}"
	}

	object Okhttp {
		const val OkHttp = "com.squareup.okhttp3:okhttp:${Versions.Okhttp.OkhttpVersion}"
		const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Okhttp.OkhttpVersion}"
	}

	object Retrofit {
		const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.RetrofitVersion}"
		const val GsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.RetrofitVersion}"
	}

	object Glide {
		const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide.GlideVersion}"
		const val Compiler = "com.github.bumptech.glide:compiler:${Versions.Glide.CompilerVersion}"
	}

	object Log {
		const val Timber = "com.jakewharton.timber:timber:${Versions.Timber.TimberVersion}"
	}

	object Test {
		const val JUnitJupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.Test.JUnitVersion}"
		const val JUnitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.Test.JUnitVersion}"
		const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.Coroutine}"
	}
}