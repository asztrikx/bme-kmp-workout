package hu.asztrikx.workout.service.stats

import kotlinx.datetime.LocalDate

data class QuantityWithDate(
	val count: Float,
	val date: LocalDate,
)