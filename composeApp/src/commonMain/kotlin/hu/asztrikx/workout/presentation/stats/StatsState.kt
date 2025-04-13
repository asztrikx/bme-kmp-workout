package hu.asztrikx.workout.presentation.stats

import hu.asztrikx.workout.model.Category
import hu.asztrikx.workout.model.QuantityWithDate
import kotlinx.datetime.LocalDate

sealed class StatsState {
	data object Loading: StatsState()
	data class Error(val error: Throwable): StatsState()
	data class Result(
		private val quantityWithDates: List<List<QuantityWithDate>>,
		val categories: List<Category>,
		val startDate: LocalDate,
	): StatsState() {
		fun quantityWithDates() =
			quantityWithDates.map { quantityWithDates ->
				quantityWithDates.filter { quantityWithDate ->
					quantityWithDate.date >= startDate
				}
			}
	}
}