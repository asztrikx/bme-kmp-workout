package hu.asztrikx.workout.presentation.mapper

import hu.asztrikx.workout.service.log.Log
import kotlinx.datetime.LocalDate

data class LogUI (
	val id: Long,
	val date: LocalDate,
	val quantities: List<QuantityUI>,
	val expanded: Boolean,
)

fun LogUI.asModel() = Log(
	id,
	date,
	quantities.map { it.asModel() }
)

fun Log.asUI() = LogUI(
	id,
	date,
	quantities.map { it.asUI() },
	false,
)