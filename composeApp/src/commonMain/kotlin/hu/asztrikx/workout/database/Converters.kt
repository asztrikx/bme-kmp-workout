package hu.asztrikx.workout.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

object Converters {
	@TypeConverter
	fun LocalDate.asString() =
		toString()

	@TypeConverter
	fun String.asLocalDate() =
		LocalDate.parse(this)
}