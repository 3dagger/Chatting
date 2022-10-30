plugins {
	id("com.android.library")
	kotlin("android")
	kotlin("kapt")
	id("dagger.hilt.android.plugin")
}

android {
	compileSdk = ConfigData.CompileSdkVersion

	defaultConfig {
		minSdk = ConfigData.MinSdkVersion
		targetSdk = ConfigData.TargetSdkVersion

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	implementation(project(ProjectConstants.Domain))
	implementation(Dependencies.AndroidSupport.AppCompat)
	implementation(Dependencies.Kotlin.StdLib)
	implementation(Dependencies.Coroutines.Core)
	implementation(Dependencies.Coroutines.Android)
	implementation(Dependencies.Okhttp.OkHttp)
	implementation(Dependencies.Okhttp.LoggingInterceptor)
	implementation(Dependencies.Retrofit.Retrofit)
	implementation(Dependencies.Retrofit.GsonConverter)
	implementation(Dependencies.Hilt.Android)
	kapt(Dependencies.Hilt.Compiler)
}