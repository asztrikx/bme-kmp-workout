package hu.asztrikx.workout.model

import kotlinx.datetime.LocalDate

data class QuantityWithDate(
	val count: Float,
	val date: LocalDate,
)