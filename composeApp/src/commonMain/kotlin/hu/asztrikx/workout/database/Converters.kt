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

	// From extension functions KSP generates Converters.foo()
}