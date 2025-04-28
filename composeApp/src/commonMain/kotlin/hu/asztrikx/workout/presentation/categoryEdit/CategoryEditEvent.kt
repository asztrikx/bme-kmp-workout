package hu.asztrikx.workout.presentation.categoryEdit

import androidx.compose.ui.graphics.vector.ImageVector

sealed class CategoryEditEvent {
	data class Name(val name: String): CategoryEditEvent()
	data class Unit(val unit: String): CategoryEditEvent()
	data class Icon(val icon: ImageVector): CategoryEditEvent()
	data object Save: CategoryEditEvent()
}