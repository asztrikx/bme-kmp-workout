package hu.asztrikx.workout.stats

import kotlinx.datetime.LocalDate

sealed class StatsEvent {
	data class StartDate(val startDate: LocalDate): StatsEvent()
}