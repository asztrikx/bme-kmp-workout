package hu.asztrikx.workout.database.settings

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import hu.asztrikx.workout.database.category.CategoryEntity
import hu.asztrikx.workout.service.settings.Settings
import kotlinx.datetime.LocalDate

@Entity
data class SettingsEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val startDate: LocalDate,
)

data class SettingsWithCategories(
	@Embedded
	val settings: SettingsEntity,

	@Relation(
		parentColumn = "id",
		entityColumn = "settingsId"
	)
	val categories: List<CategoryEntity>
)

fun Settings.asEntity() =
	SettingsEntity(
		id = 1,
		startDate,
	)