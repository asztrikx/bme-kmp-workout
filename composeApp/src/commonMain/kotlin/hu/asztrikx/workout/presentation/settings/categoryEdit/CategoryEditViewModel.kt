package hu.asztrikx.workout.presentation.settings.categoryEdit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Wash
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.database.category.CategoryService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryEditViewModel(
	private val service: CategoryService,
): ViewModel() {
	private val _state = MutableStateFlow(CategoryUI(-1, Icons.Default.Wash, "", ""))
	val state = _state.asStateFlow()

	private val _uiEvent = Channel<CategoryEditUIEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	fun new() {
		_state.update {
			CategoryUI(-1, Icons.Default.FitnessCenter, "", "")
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
			is CategoryEditEvent.Icon -> {
				_state.update { it.copy(icon = event.icon) }
			}
			is CategoryEditEvent.Save -> {
				viewModelScope.launch {
					service.update(state.value.asModel())
					_uiEvent.send(CategoryEditUIEvent.Success)
				}
			}
		}
	}
}