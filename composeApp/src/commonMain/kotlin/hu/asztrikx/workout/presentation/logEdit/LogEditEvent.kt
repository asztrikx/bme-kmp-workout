package hu.asztrikx.workout.presentation.logEdit

import kotlinx.datetime.LocalDate

sealed class LogEditEvent {
	data class Date(val date: LocalDate): LogEditEvent()
	data class Category(val count: Float?, val id: Int): LogEditEvent()
	data object Save: LogEditEvent()
}