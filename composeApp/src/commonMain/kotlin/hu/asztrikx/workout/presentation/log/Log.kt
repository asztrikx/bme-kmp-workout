package hu.asztrikx.workout.presentation.log

import hu.asztrikx.workout.presentation.quantity.Quantity
import kotlinx.datetime.LocalDate

data class Log(
	val id: Int,
	val date: LocalDate,
	val quantities: List<Quantity>,
)