package hu.asztrikx.workout.service.log

import hu.asztrikx.workout.service.quantity.Quantity
import kotlinx.datetime.LocalDate

data class Log(
	val id: Long,
	val date: LocalDate,
	val quantities: List<Quantity>,
)