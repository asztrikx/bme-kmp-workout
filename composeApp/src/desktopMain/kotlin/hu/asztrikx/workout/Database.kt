package hu.asztrikx.workout

import androidx.room.Room
import androidx.room.RoomDatabase
import hu.asztrikx.workout.database.WorkoutDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<WorkoutDatabase> {
	val dbFile = File(System.getProperty("java.io.tmpdir"), "workout.db") // TODO tmp dir??
	return Room.databaseBuilder<WorkoutDatabase>(
		name = dbFile.absolutePath,
	)
}