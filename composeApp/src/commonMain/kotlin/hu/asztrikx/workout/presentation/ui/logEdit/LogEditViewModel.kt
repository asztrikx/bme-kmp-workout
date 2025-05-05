package hu.asztrikx.workout.presentation.ui.logEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.database.GENERATE
import hu.asztrikx.workout.presentation.mapper.LogUI
import hu.asztrikx.workout.presentation.mapper.asModel
import hu.asztrikx.workout.presentation.mapper.asUI
import hu.asztrikx.workout.service.category.CategoryService
import hu.asztrikx.workout.service.log.LogService
import hu.asztrikx.workout.service.log.Log
import hu.asztrikx.workout.service.quantity.Quantity
import hu.asztrikx.workout.presentation.ui.shared.currentDate
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LogEditViewModel(
	private val service: LogService,
	private val categoryService: CategoryService,
): ViewModel() {
	private val _state = MutableStateFlow(LogUI(GENERATE, currentDate(), listOf(), false))
	val state = _state.asStateFlow()

	private val _uiEvent = Channel<LogEditUIEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	fun new() {
		viewModelScope.launch {
			val categories = categoryService.getAllNotDeleted().first()
			val quantities = categories.map {
				Quantity(GENERATE, it, null)
			}
			_state.update { Log(GENERATE, currentDate(), quantities).asUI() }
		}
	}

	fun edit(id: Long) {
		viewModelScope.launch {
			service.getAllWithQuantityAndCategory().collect { logs ->
				val log = logs.find { it.id == id }!!
				_state.update { log.asUI() }
			}
		}
	}

	fun onEvent(event: LogEditEvent) {
		when (event) {
			is LogEditEvent.Date -> {
				_state.update { it.copy(date = event.date) }
			}
			is LogEditEvent.Category -> {
				_state.update { it.copy(quantities = it.quantities.map { quantity ->
					if (quantity.category.id == event.categoryId) {
						quantity.copy(count = event.count)
					} else
						quantity
				})}
			}
			is LogEditEvent.Save -> {
				viewModelScope.launch {
					_state.value.let {
						if (it.id == GENERATE) {
							service.insert(it.asModel())
						} else {
							service.update(it.asModel())
						}
					}
					_uiEvent.send(LogEditUIEvent.Success)
				}
			}
		}
	}
}