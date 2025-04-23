package hu.asztrikx.workout.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.database.settings.SettingsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(private val service: SettingsService): ViewModel() {
	private val _state = MutableStateFlow<SettingsState>(
		SettingsState.Loading
	)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
		viewModelScope.launch {
			try {
				_state.update { SettingsState.Loading }
				service.getAllWithCategories().collect { settings ->
					_state.update { SettingsState.Result(settings.first())}
				}
			} catch (e: Exception) {
				_state.value = SettingsState.Error(e)
			}
		}
	}

	fun onEvent(event: SettingsEvent) = viewModelScope.launch {
		val settings = (_state.value as SettingsState.Result).settings
		when (event) {
			is SettingsEvent.DateChange -> {
				service.update(settings.copy(startDate = event.startDate))
			}
		}
	}
}