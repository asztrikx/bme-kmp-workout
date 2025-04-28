package hu.asztrikx.workout.service.settings

import hu.asztrikx.workout.service.category.Category
import kotlinx.datetime.LocalDate

data class Settings (
	val startDate: LocalDate,
	val categories: List<Category>
)