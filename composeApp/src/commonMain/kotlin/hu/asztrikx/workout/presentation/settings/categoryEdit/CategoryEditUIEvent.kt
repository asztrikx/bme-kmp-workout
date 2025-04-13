package hu.asztrikx.workout.presentation.settings.categoryEdit

sealed class CategoryEditUIEvent {
	data object Success: CategoryEditUIEvent()
}