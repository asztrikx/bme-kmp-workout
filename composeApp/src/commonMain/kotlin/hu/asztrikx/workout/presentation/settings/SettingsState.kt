package hu.asztrikx.workout.presentation.settings

import hu.asztrikx.workout.service.settings.Settings

sealed class SettingsState {
	data object Loading: SettingsState()
	data class Error(val error: Throwable): SettingsState()
	data class Result(val settings: Settings): SettingsState()
}