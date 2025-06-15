package hu.asztrikx.workout.presentation.mapper

import hu.asztrikx.workout.service.settings.Settings
import kotlinx.datetime.LocalDate

data class SettingsUI (
	val startDate: LocalDate,
	val leftHanded: Boolean,
	val categories: List<CategoryUI>
)

fun SettingsUI.asModel() = Settings(
	startDate,
	leftHanded,
	categories.map { it.asModel() }
)

fun Settings.asUI() = SettingsUI(
	startDate,
	leftHanded,
	categories.map { it.asUI() }
)
