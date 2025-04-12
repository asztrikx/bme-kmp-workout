package hu.asztrikx.workout.logEdit

sealed class LogEditUIEvent {
	data object Success: LogEditUIEvent()
}