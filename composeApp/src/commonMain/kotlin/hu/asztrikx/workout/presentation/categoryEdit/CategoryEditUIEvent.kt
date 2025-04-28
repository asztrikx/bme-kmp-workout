package hu.asztrikx.workout.presentation.categoryEdit

sealed class CategoryEditUIEvent {
	data object Success: CategoryEditUIEvent()
}