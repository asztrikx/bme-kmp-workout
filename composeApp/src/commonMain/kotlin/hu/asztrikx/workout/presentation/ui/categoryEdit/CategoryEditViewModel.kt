package hu.asztrikx.workout.presentation.ui.categoryEdit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Wash
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.database.GENERATE
import hu.asztrikx.workout.presentation.mapper.CategoryUI
import hu.asztrikx.workout.presentation.mapper.asModel
import hu.asztrikx.workout.presentation.mapper.asUI
import hu.asztrikx.workout.service.category.CategoryService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryEditViewModel(
	private val service: CategoryService,
): ViewModel() {
	private val _state = MutableStateFlow(CategoryUI(-1, Icons.Default.Wash, "", "", ""))
	val state = _state.asStateFlow()

	private val _uiEvent = Channel<CategoryEditUIEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	fun new() {
		_state.update {
			CategoryUI(GENERATE, Icons.Default.FitnessCenter, "FitnessCenter", "", "")
		}
	}

	fun edit(id: Long) {
		viewModelScope.launch {
			service.find(id).collect { item ->
				_state.update { item.asUI() }
			}
		}
	}

	fun onEvent(event: CategoryEditEvent) {
		when (event) {
			is CategoryEditEvent.Name -> {
				_state.update { it.copy(name = event.name) }
			}
			is CategoryEditEvent.Unit -> {
				_state.update { it.copy(unit = event.unit) }
			}
			is CategoryEditEvent.IconName -> {
				_state.update { it.copy(icon = event.icon, iconName = event.name) }
			}
			is CategoryEditEvent.Save -> {
				viewModelScope.launch {
					_state.value.asModel().let {
						if (it.id == GENERATE) {
							service.insert(it)
						} else {
							service.update(it)
						}
					}
					_uiEvent.send(CategoryEditUIEvent.Success)
				}
			}
		}
	}
}