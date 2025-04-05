package hu.asztrikx.workout

import android.app.Application
import org.koin.android.ext.koin.androidContext

class WorkoutApplication: Application() {
	override fun onCreate() {
		super.onCreate()
		initKoin {
			androidContext(this@WorkoutApplication)
		}
	}
}