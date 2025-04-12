package hu.asztrikx.workout.quantity

import hu.asztrikx.workout.category.Category

data class Quantity(
	val id: Int,
	val category: Category,
	val count: Float?,
)
