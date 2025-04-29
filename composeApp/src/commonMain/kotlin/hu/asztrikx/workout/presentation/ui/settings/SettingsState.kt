package hu.asztrikx.workout.presentation.ui.settings

import hu.asztrikx.workout.presentation.mapper.SettingsUI

sealed class SettingsState {
	data object Loading: SettingsState()
	data class Error(val error: Throwable): SettingsState()
	data class Result(val settings: SettingsUI): SettingsState()
}