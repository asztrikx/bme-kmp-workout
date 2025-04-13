package hu.asztrikx.workout.presentation.logEdit

sealed class LogEditUIEvent {
	data object Success: LogEditUIEvent()
}