package hu.asztrikx.workout.settings

import hu.asztrikx.workout.category.Category
import kotlinx.datetime.LocalDate

data class Settings (
	val startDate: LocalDate,
	val categories: List<Category>
)