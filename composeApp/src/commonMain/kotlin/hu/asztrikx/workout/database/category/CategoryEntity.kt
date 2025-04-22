package hu.asztrikx.workout.database.category

import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.asztrikx.workout.model.Category

@Entity
data class CategoryEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val icon: String,
	val name: String,
	val unit: String,
	val settingsId: Int,
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