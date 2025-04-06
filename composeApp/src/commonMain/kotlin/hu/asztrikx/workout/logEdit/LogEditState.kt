package hu.asztrikx.workout.logEdit

import hu.asztrikx.workout.log.Log

sealed class LogEditState {
	data object Loading: LogEditState()
	data class Error(val error: Throwable): LogEditState()
	data class Result(val log: Log): LogEditState()
}