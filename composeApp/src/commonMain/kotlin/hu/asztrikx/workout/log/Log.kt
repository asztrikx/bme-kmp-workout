package hu.asztrikx.workout.log

import hu.asztrikx.workout.quantity.Quantity
import kotlinx.datetime.LocalDateTime

data class Log(
	val dateTime: LocalDateTime,
	val quantities: List<Quantity>,
)