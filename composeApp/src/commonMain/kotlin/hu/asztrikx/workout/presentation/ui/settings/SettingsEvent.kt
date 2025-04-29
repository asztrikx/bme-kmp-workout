package hu.asztrikx.workout.presentation.ui.settings

import kotlinx.datetime.LocalDate

sealed class SettingsEvent {
	data class DateChange(val startDate: LocalDate): SettingsEvent()
}