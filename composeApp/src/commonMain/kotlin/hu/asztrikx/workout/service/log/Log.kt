package hu.asztrikx.workout.service.log

import hu.asztrikx.workout.database.log.LogEntity
import hu.asztrikx.workout.database.log.LogWithQuantitiesAndCategories
import hu.asztrikx.workout.service.quantity.Quantity
import hu.asztrikx.workout.service.quantity.asModel
import kotlinx.datetime.LocalDate

data class Log(
	val id: Long,
	val date: LocalDate,
	val quantities: List<Quantity>,
)

fun LogWithQuantitiesAndCategories.asModel() = Log(
	id = logEntity.id,
	date = logEntity.date,
	quantities = quantityWithCategories.map { it.asModel() }
)

fun Log.asEntity() = LogEntity(
	id,
	date,
)