package hu.asztrikx.workout

import hu.asztrikx.workout.database.WorkoutDatabase
import hu.asztrikx.workout.database.getRoomDatabase
import org.koin.dsl.module

actual fun platformModule() = module {
	single<WorkoutDatabase> { getRoomDatabase(getDatabaseBuilder()) }
}