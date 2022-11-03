package kr.dagger.rocketpunchpretask.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

	@Provides
	@Singleton
	fun provideFirebaseAuthInstance(): FirebaseAuth {
		return FirebaseAuth.getInstance()
	}

	@Provides
	@Singleton
	fun provideFirebaseDatabaseInstance(): FirebaseDatabase {
		return FirebaseDatabase.getInstance()
	}

	@Provides
	@Singleton
	fun provideFirebaseRealtimeDataBase(database: FirebaseDatabase) : DatabaseReference {
		return database.reference.child("asdf")
	}


}