package hu.asztrikx.workout.presentation.category

import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
	val id: Int,
	val icon: ImageVector,
	val name: String,
	val unit: String,
)
