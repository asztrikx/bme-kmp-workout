package hu.asztrikx.workout.presentation.mapper

import hu.asztrikx.workout.service.quantity.Quantity

data class QuantityUI (
	val id: Long,
	val category: CategoryUI,
	val count: Float?,
)

fun QuantityUI.asModel() = Quantity(
	id,
	category.asModel(),
	count,
)

fun Quantity.asUI() = QuantityUI(
	id,
	category.asUI(),
	count,
)