package hu.asztrikx.workout.presentation.settings

import hu.asztrikx.workout.presentation.category.Category
import kotlinx.datetime.LocalDate

data class Settings (
	val startDate: LocalDate,
	val categories: List<Category>
)