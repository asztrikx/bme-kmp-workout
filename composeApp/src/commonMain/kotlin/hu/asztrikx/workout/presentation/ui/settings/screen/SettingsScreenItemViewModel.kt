package hu.asztrikx.workout.presentation.ui.settings.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.presentation.mapper.CategoryUI
import hu.asztrikx.workout.presentation.mapper.asModel
import hu.asztrikx.workout.service.category.CategoryService
import kotlinx.coroutines.launch

class SettingsScreenItemViewModel(private val service: CategoryService): ViewModel() {
	fun delete(item: CategoryUI) {
		viewModelScope.launch {
			service.delete(item.asModel())
		}
	}
}