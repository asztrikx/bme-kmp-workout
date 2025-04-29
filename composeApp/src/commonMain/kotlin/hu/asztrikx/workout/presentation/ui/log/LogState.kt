package hu.asztrikx.workout.presentation.ui.log

import hu.asztrikx.workout.service.log.Log

sealed class LogState {
	data object Loading: LogState()
	data class Error(val error: Throwable): LogState()
	data class Result(val logs: List<Log>): LogState()
}