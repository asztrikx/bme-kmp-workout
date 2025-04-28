package hu.asztrikx.workout.service.quantity

import hu.asztrikx.workout.database.quantity.QuantityWithCategory
import hu.asztrikx.workout.service.category.Category
import hu.asztrikx.workout.service.category.asModel

data class Quantity(
	val id: Long,
	val category: Category,
	val count: Float?,
)

fun QuantityWithCategory.asModel() =
	Quantity(
		quantityEntity.id,
		categoryEntity.asModel(),
		quantityEntity.count,
	)