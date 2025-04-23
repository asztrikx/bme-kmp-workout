package hu.asztrikx.workout.presentation.logEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.database.log.LogService
import hu.asztrikx.workout.model.Log
import hu.asztrikx.workout.presentation.shared.currentDate
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LogEditViewModel(private val service: LogService): ViewModel() {
	private val _state = MutableStateFlow(Log(-1, currentDate(), listOf()))
	val state = _state.asStateFlow()

	private val _uiEvent = Channel<LogEditUIEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	fun new() {
		_state.update { Log(-1, currentDate(), listOf()) }
	}

	fun edit(id: Long) {
		viewModelScope.launch {
			service.getAllWithQuantityAndCategory().collect { logs ->
				val log = logs.find { it.id == id }!!
				_state.update { log }
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
					if (quantity.id == event.id) {
						quantity.copy(count = event.count)
					} else
						quantity
				})}
			}
			is LogEditEvent.Save -> {
				viewModelScope.launch {
					_state.value.let {
						if (it.id == -1L) {
							service.insert(it)
						} else {
							service.update(it)
						}
					}
					_uiEvent.send(LogEditUIEvent.Success)
				}
			}
		}
	}
}