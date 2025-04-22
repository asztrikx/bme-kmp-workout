package hu.asztrikx.workout

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.asztrikx.workout.database.WorkoutDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<WorkoutDatabase> {
	val appContext = ctx.applicationContext
	val dbFile = appContext.getDatabasePath("workout.db")
	return Room.databaseBuilder<WorkoutDatabase>(
		context = appContext,
		name = dbFile.absolutePath
	)
}