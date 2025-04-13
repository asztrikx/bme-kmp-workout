package hu.asztrikx.workout.model

import kotlinx.datetime.LocalDate

data class Log(
	val id: Int,
	val date: LocalDate,
	val quantities: List<Quantity>,
)