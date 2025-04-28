package hu.asztrikx.workout.presentation.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.service.stats.StatsService
import hu.asztrikx.workout.presentation.shared.currentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StatsViewModel(private val service: StatsService): ViewModel() {
	private val _state = MutableStateFlow<StatsState>(StatsState.Loading)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
		viewModelScope.launch {
			try {
				_state.update { StatsState.Loading }
				service.getAll().collect { statsByCategory ->
					_state.update { StatsState.Result(
						statsByCategory,
						currentDate()
					)}
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