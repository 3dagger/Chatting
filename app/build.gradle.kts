plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("de.mannodermaus.android-junit5")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    namespace = "kr.dagger.chat"
    compileSdk = ConfigData.CompileSdkVersion

    defaultConfig {
        applicationId = "kr.dagger.chat"
        minSdk = ConfigData.MinSdkVersion
        targetSdk = ConfigData.TargetSdkVersion
        versionCode = ConfigData.VersionCode
        versionName = ConfigData.VersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(ProjectConstants.Data))
    implementation(project(ProjectConstants.Domain))

    implementation(Dependencies.Kotlin.StdLib)
    implementation(Dependencies.AndroidSupport.CoreKtx)
    implementation(Dependencies.AndroidSupport.AppCompat)
    implementation(Dependencies.AndroidSupport.Material)
    implementation(Dependencies.AndroidSupport.ConstraintLayout)
    implementation(Dependencies.AndroidSupport.CardView)
    implementation(Dependencies.AndroidSupport.ViewPager2)
    implementation(Dependencies.AndroidSupport.RecyclerView)
    implementation(Dependencies.AndroidSupport.ActivityKtx)
    implementation(Dependencies.AndroidSupport.FragmentKtx)
    implementation(Dependencies.AndroidSupport.SwipeRefreshLayout)
    implementation(Dependencies.AndroidSupport.Navigation.FragmentKtx)
    implementation(Dependencies.AndroidSupport.Navigation.UiKtx)
    implementation(Dependencies.AndroidSupport.LifeCycle.ViewModelKtx)
    implementation(Dependencies.AndroidSupport.LifeCycle.LiveDataKtx)
    implementation(Dependencies.AndroidSupport.LifeCycle.Runtime)
    implementation(Dependencies.Coroutines.Core)
    implementation(Dependencies.Coroutines.Android)
    implementation(Dependencies.Okhttp.OkHttp)
    implementation(Dependencies.Okhttp.LoggingInterceptor)
    implementation(Dependencies.Retrofit.Retrofit)
    implementation(Dependencies.Retrofit.GsonConverter)
    implementation(Dependencies.Log.Timber)

    implementation(Dependencies.Hilt.Android)
    implementation("androidx.test.ext:junit-ktx:1.1.3")
    androidTestImplementation("junit:junit:4.12")
    kapt(Dependencies.Hilt.Compiler)

    implementation(Dependencies.Glide.Glide)
    kapt(Dependencies.Glide.Compiler)

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // Google
    implementation("com.google.android.gms:play-services-auth:20.3.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:31.0.2"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
}