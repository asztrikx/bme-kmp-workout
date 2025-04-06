package hu.asztrikx.workout.settings

sealed class SettingsState {
	data object Loading: SettingsState()
	data class Error(val error: Throwable): SettingsState()
	data class Result(val settings: Settings): SettingsState()
}