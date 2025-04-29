package hu.asztrikx.workout.presentation.mapper

import hu.asztrikx.workout.service.settings.Settings
import kotlinx.datetime.LocalDate

data class SettingsUI (
	val startDate: LocalDate,
	val categories: List<CategoryUI>
)

fun SettingsUI.asModel() = Settings(
	startDate,
	categories.map { it.asModel() }
)

fun Settings.asUI() = SettingsUI(
	startDate,
	categories.map { it.asUI() }
)
