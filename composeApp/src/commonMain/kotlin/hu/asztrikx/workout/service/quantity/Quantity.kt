package hu.asztrikx.workout.service.quantity

import hu.asztrikx.workout.service.category.Category

data class Quantity(
	val id: Long,
	val category: Category,
	val count: Float?,
)
