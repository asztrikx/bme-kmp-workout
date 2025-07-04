package hu.asztrikx.workout.database.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,
	val iconName: String,
	val name: String,
	val unit: String,
	val settingsId: Int,
	val isDeleted: Boolean,
)
