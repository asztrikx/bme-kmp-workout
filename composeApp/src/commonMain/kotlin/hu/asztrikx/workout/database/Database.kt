package hu.asztrikx.workout.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getRoomDatabase(
	builder: RoomDatabase.Builder<WorkoutDatabase>
): WorkoutDatabase {
	return builder
		.addMigrations()
		.fallbackToDestructiveMigrationOnDowngrade(true)
		.setDriver(BundledSQLiteDriver())
		.setQueryCoroutineContext(Dispatchers.IO)
		.build()
}