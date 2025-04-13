package hu.asztrikx.workout.presentation.log

import hu.asztrikx.workout.model.Log

sealed class LogState {
	data object Loading: LogState()
	data class Error(val error: Throwable): LogState()
	data class Result(val logs: List<Log>): LogState()
}