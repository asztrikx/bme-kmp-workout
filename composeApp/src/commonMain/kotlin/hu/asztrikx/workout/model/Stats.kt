package hu.asztrikx.workout.model

data class Stats(
	val category: Category,
	val stats: List<QuantityWithDate>,
)