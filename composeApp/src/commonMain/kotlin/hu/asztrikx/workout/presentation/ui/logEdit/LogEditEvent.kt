package hu.asztrikx.workout.presentation.ui.logEdit

import kotlinx.datetime.LocalDate

sealed class LogEditEvent {
	data class Date(val date: LocalDate): LogEditEvent()
	data class Category(val count: Float?, val categoryId: Long): LogEditEvent()
	data object Save: LogEditEvent()
}