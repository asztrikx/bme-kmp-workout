package hu.asztrikx.workout.presentation.ui.logEdit

sealed class LogEditUIEvent {
	data object Success: LogEditUIEvent()
}