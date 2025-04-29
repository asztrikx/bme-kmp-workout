package hu.asztrikx.workout.presentation.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.presentation.mapper.asUI
import hu.asztrikx.workout.service.stats.StatsService
import hu.asztrikx.workout.service.settings.SettingsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StatsViewModel(
	private val service: StatsService,
	private val settingsService: SettingsService,
): ViewModel() {
	private val _state = MutableStateFlow<StatsState>(StatsState.Loading)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
		viewModelScope.launch {
			try {
				_state.update { StatsState.Loading }
				combine(
					settingsService.getWithCategories(),
					service.getAll(),
				) { settings, statsByCategory ->
					StatsState.Result(
						statsByCategory.map { it.asUI() },
						settings!!.startDate,
					)
				}.collect { result ->
					_state.update { result }
				}
			} catch (e: Exception) {
				_state.update { StatsState.Error(e) }
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