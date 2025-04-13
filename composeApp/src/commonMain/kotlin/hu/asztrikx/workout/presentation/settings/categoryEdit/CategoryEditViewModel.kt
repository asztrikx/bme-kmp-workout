package hu.asztrikx.workout.presentation.settings.categoryEdit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Wash
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.model.Category
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryEditViewModel: ViewModel() {
	private val _state = MutableStateFlow(Category(-1, Icons.Default.Wash, "", ""))
	val state = _state.asStateFlow()

	private val _uiEvent = Channel<CategoryEditUIEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	fun new() {
		_state.update {
			Category(-1, Icons.Default.FitnessCenter, "", "")
		}
	}

	fun edit(id: Int) {
		_state.update {
			Category(1, Icons.Default.FitnessCenter, "Wash", "count")
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
				// TODO save
				viewModelScope.launch {
					_uiEvent.send(CategoryEditUIEvent.Success)
				}
			}
		}
	}
}