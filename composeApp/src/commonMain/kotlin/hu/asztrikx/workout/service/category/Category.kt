package hu.asztrikx.workout.service.category

import hu.asztrikx.workout.database.category.CategoryEntity

data class Category(
	val id: Long,
	val iconName: String,
	val name: String,
	val unit: String,
)

fun CategoryEntity.asModel() = Category(
	id,
	iconName,
	name,
	unit,
)

fun Category.asEntity(isDeleted: Boolean) = CategoryEntity(
	id,
	iconName,
	name,
	unit,
	1,
	isDeleted,
)