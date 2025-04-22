package hu.asztrikx.workout.presentation.settings.categoryEdit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistWalker
import androidx.compose.ui.graphics.vector.ImageVector
import hu.asztrikx.workout.model.Category

data class CategoryUI(
	val id: Long,
	val icon: ImageVector,
	val name: String,
	val unit: String,
)

fun CategoryUI.asModel() = Category(
	id,
	icon = "icon", // TODO
	name,
	unit,
)

fun Category.asUI() = CategoryUI(
	id,
	icon = Icons.Default.AssistWalker, // TODO
	name,
	unit,
)