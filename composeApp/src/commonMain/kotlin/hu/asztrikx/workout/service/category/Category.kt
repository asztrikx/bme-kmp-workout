package hu.asztrikx.workout.service.category

import hu.asztrikx.workout.database.category.CategoryEntity

data class Category(
	val id: Long,
	val icon: String,
	val name: String,
	val unit: String,
)

fun CategoryEntity.asModel() = Category(
	id,
	icon,
	name,
	unit,
)

fun Category.asEntity() = CategoryEntity(
	id,
	icon,
	name,
	unit,
	1,
)