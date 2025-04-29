package hu.asztrikx.workout.presentation.ui.stats

import hu.asztrikx.workout.presentation.mapper.StatsUI
import kotlinx.datetime.LocalDate

sealed class StatsState {
	data object Loading: StatsState()
	data class Error(val error: Throwable): StatsState()
	data class Result(
		private val statsByCategory: List<StatsUI>,
		val startDate: LocalDate,
	): StatsState() {
		fun statsByCategory() =
			statsByCategory.map { stats ->
				stats.category to
				stats.stats.filter {
					it.date >= startDate
				}
			}
	}
}