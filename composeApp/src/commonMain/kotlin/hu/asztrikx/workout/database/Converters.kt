package hu.asztrikx.workout.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

object Converters {
	@TypeConverter
	fun asString(localDate: LocalDate) =
		localDate.toString()

	@TypeConverter
	fun asLocalDate(string: String) =
		LocalDate.parse(string)

	/*
	// KSP tries to use these as Converters.asString()
	@TypeConverter
	fun LocalDate.asString(): String =
		toString()

	@TypeConverter
	fun String.asLocalDate(): LocalDate =
		LocalDate.parse(this)

	*/
}