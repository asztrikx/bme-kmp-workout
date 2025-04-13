package hu.asztrikx.workout.presentation.quantity

import hu.asztrikx.workout.presentation.category.Category

data class Quantity(
	val id: Int,
	val category: Category,
	val count: Float?,
)
