package hu.asztrikx.workout.presentation.stats

import kotlinx.datetime.LocalDate

data class QuantityWithDate(
	val count: Float,
	val date: LocalDate,
)