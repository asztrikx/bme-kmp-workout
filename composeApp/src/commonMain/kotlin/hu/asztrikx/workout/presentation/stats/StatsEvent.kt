package hu.asztrikx.workout.presentation.stats

import kotlinx.datetime.LocalDate

sealed class StatsEvent {
	data class StartDate(val startDate: LocalDate): StatsEvent()
}