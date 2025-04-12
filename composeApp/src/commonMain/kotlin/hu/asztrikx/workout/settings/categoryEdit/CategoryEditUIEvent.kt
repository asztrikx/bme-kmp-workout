package hu.asztrikx.workout.settings.categoryEdit

sealed class CategoryEditUIEvent {
	data object Success: CategoryEditUIEvent()
}