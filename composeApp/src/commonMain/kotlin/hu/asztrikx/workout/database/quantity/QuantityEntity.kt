package hu.asztrikx.workout.database.quantity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import hu.asztrikx.workout.database.category.CategoryEntity
import hu.asztrikx.workout.database.category.asModel
import hu.asztrikx.workout.model.Quantity

@Entity
data class QuantityEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val categoryId: Long,
	val logId: Long,
	val count: Float?,
)

data class QuantityWithCategory(
	@Embedded
	val quantityEntity: QuantityEntity,

	@Relation(
		parentColumn = "categoryId",
		entityColumn = "id",
	)
	val categoryEntity: CategoryEntity,
)

fun QuantityWithCategory.asModel() =
	Quantity(
		quantityEntity.id,
		categoryEntity.asModel(),
		quantityEntity.count,
	)