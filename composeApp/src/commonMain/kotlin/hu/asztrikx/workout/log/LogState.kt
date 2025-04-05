package hu.asztrikx.workout.log

sealed class LogState {
	data object Loading: LogState()
	data class Error(val error: Throwable): LogState()
	data class Result(val logs: List<Log>): LogState()
}