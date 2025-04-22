package hu.asztrikx.workout.model

import kotlinx.datetime.LocalDate

data class Log(
	val id: Long,
	val date: LocalDate,
	val quantities: List<Quantity>,
)