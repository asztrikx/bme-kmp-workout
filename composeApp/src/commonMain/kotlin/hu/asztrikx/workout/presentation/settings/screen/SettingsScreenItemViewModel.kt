package hu.asztrikx.workout.presentation.settings.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.service.category.CategoryService
import hu.asztrikx.workout.service.category.Category
import kotlinx.coroutines.launch

class SettingsScreenItemViewModel(private val service: CategoryService): ViewModel() {
	fun delete(item: Category) {
		viewModelScope.launch {
			service.delete(item)
		}
	}
}