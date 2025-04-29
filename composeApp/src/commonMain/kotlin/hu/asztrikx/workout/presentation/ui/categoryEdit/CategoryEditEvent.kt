package hu.asztrikx.workout.presentation.ui.categoryEdit

import androidx.compose.ui.graphics.vector.ImageVector

sealed class CategoryEditEvent {
	data class Name(val name: String): CategoryEditEvent()
	data class Unit(val unit: String): CategoryEditEvent()
	data class IconName(val icon: ImageVector, val name: String): CategoryEditEvent()
	data object Save: CategoryEditEvent()
}