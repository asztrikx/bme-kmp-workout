package hu.asztrikx.workout.presentation.ui.categoryEdit

sealed class CategoryEditUIEvent {
	data object Success: CategoryEditUIEvent()
}