package hu.asztrikx.workout.service.settings

import hu.asztrikx.workout.database.settings.SettingsEntity
import hu.asztrikx.workout.database.settings.SettingsWithCategories
import hu.asztrikx.workout.service.category.Category
import hu.asztrikx.workout.service.category.asModel
import kotlinx.datetime.LocalDate

data class Settings (
	val startDate: LocalDate,
	val leftHanded: Boolean,
	val categories: List<Category>
)

fun Settings.asEntity() =
	SettingsEntity(
		id = 1,
		leftHanded,
		startDate,
	)

fun SettingsWithCategories.asModel() =
	Settings(
		startDate = settings.startDate,
		leftHanded = settings.leftHanded,
		categories = categories.map { it.asModel() },
	)
