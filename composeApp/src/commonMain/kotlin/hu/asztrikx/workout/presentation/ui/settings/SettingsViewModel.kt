package hu.asztrikx.workout.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.presentation.mapper.asModel
import hu.asztrikx.workout.presentation.mapper.asUI
import hu.asztrikx.workout.service.settings.SettingsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(private val service: SettingsService): ViewModel() {
	private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
		viewModelScope.launch {
			try {
				_state.update { SettingsState.Loading }
				service.getWithCategories().collect { settings ->
					_state.update { SettingsState.Result(settings!!.asUI())}
				}
			} catch (e: Exception) {
				_state.update { SettingsState.Error(e) }
			}
		}
	}

	fun onEvent(event: SettingsEvent) = viewModelScope.launch {
		val settings = (_state.value as SettingsState.Result).settings
		when (event) {
			is SettingsEvent.DateChange -> {
				service.update(settings.copy(startDate = event.startDate).asModel())
			}
			is SettingsEvent.LeftHandedChange -> {
				service.update(settings.copy(leftHanded = event.leftHanded).asModel())
			}
		}
	}

	fun export() {
		viewModelScope.launch {
			service.export()
		}
	}
}