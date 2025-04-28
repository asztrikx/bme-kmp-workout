package hu.asztrikx.workout.service.stats

import hu.asztrikx.workout.service.category.Category

data class Stats(
	val category: Category,
	val stats: List<QuantityWithDate>,
)