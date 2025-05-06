package hu.asztrikx.workout.database.stats

import kotlinx.datetime.LocalDate

// Not stored
data class StatEntity(
    val categoryName: String,
    val categoryUnit: String,
    val categoryIconName: String,
    val count: Float?,
    val logDate: LocalDate
)