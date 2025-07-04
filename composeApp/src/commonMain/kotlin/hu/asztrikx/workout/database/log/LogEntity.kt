package hu.asztrikx.workout.database.log

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import hu.asztrikx.workout.database.quantity.QuantityEntity
import hu.asztrikx.workout.database.quantity.QuantityWithCategory
import kotlinx.datetime.LocalDate

@Entity
data class LogEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val date: LocalDate,
)

data class LogWithQuantitiesAndCategories(
	@Embedded
	val logEntity: LogEntity,

	@Relation(
		entity = QuantityEntity::class, // Relation is also a relation class
		parentColumn = "id",
		entityColumn = "logId",
	)
	val quantityWithCategories: List<QuantityWithCategory>,
)
