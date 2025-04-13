package hu.asztrikx.workout.presentation.stats

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistWalker
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.presentation.category.Category
import hu.asztrikx.workout.presentation.shared.currentDate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class StatsViewModel: ViewModel() {
	private val _state = MutableStateFlow<StatsState>(StatsState.Loading)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
		viewModelScope.launch {
			try {
				_state.update { StatsState.Loading }
				delay(0)
				_state.update {
					val startDate = currentDate()
					val quantityWithDates = listOf(
						listOf(100f, 50f, 5f, 60f, 1f, 30f, 50f, 35f, 50f, -100f).zip(
							List(10) {
								currentDate()
							}).map {
							QuantityWithDate(it.first, it.second)
						},
						listOf(100f, 50f, 5f, 60f, 1f, 30f, 50f, 35f, 50f, -100f).zip(
							List(10) {
								currentDate()
							}).map {
							QuantityWithDate(it.first, it.second)
						}
					)

					StatsState.Result(
						quantityWithDates,
						listOf(
							Category(1, Icons.Default.AssistWalker, "Running", "km"),
							Category(2, Icons.Default.AssistWalker, "Running", "km"),
						),
						startDate,
					)
				}
			} catch (e: Exception) {
				_state.value = StatsState.Error(e)
			}
		}
	}

	fun onEvent(statsEvent: StatsEvent) {
		when (statsEvent) {
			is StatsEvent.StartDate -> {
				_state.update {
					it as StatsState.Result
					it.copy(startDate = statsEvent.startDate)
				}
			}
		}
	}
}