package hu.asztrikx.workout.quantity

import hu.asztrikx.workout.category.Category

data class Quantity(
	val category: Category,
	val count: Float?,
)
