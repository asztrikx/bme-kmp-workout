package hu.asztrikx.workout.stats

sealed class StatsState {
	data object Loading: StatsState()
	data class Error(val error: Throwable): StatsState()
	data class Result(/**/ val i:Int): StatsState()
}