package hu.asztrikx.workout.presentation.settings

import kotlinx.datetime.LocalDate

sealed class SettingsEvent {
	data class DateChange(val startDate: LocalDate): SettingsEvent()
}