package hu.asztrikx.workout.presentation.mapper

import hu.asztrikx.workout.service.stats.QuantityWithDate
import hu.asztrikx.workout.service.stats.Stats

data class StatsUI (
	val category: CategoryUI,
	val stats: List<QuantityWithDate>, // this is not ui currently
)

fun StatsUI.asModel() = Stats(
	category.asModel(),
	stats,
)

fun Stats.asUI() = StatsUI(
	category.asUI(),
	stats,
)