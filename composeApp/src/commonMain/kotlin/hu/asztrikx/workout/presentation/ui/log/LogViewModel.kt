package hu.asztrikx.workout.presentation.ui.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.presentation.mapper.LogUI
import hu.asztrikx.workout.presentation.mapper.asModel
import hu.asztrikx.workout.presentation.mapper.asUI
import hu.asztrikx.workout.service.log.LogService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LogViewModel(
	private val service: LogService
): ViewModel() {
	private val _state = MutableStateFlow<LogState>(LogState.Loading)
	val state = _state.asStateFlow()

	init {
		loadLogs()
	}

	private fun loadLogs() {
		viewModelScope.launch {
			try {
				_state.update { LogState.Loading }
				service.getAllWithQuantityAndCategory().collect { logs ->
					_state.update {
						LogState.Result(
							logs.map { it.asUI() }
								.sortedByDescending { it.date }
						)
					}
				}
			} catch (e: Exception) {
				_state.update { LogState.Error(e) }
			}
		}
	}

	fun delete(log: LogUI) {
		(_state.value as LogState.Result).let { stateValue ->
			viewModelScope.launch {
				try {
					_state.update { LogState.Loading }
					service.delete(log.asModel())
				} catch (e: Exception) {
					_state.update { LogState.Error(e) }
				}
			}
		}
	}

	fun changeExpanded(log: LogUI) {
		(_state.value as LogState.Result).let { stateValue ->
			viewModelScope.launch {
				_state.update { LogState.Result(
					stateValue.logs.map {
						if (it.id == log.id) {
							it.copy(expanded = !it.expanded)
						} else {
							it
						}
					}
				) }
			}
		}
	}
}