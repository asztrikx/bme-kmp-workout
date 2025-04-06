package hu.asztrikx.workout.log

import hu.asztrikx.workout.quantity.Quantity
import kotlinx.datetime.LocalDate

data class Log(
	val id: Int,
	val date: LocalDate,
	val quantities: List<Quantity>,
)