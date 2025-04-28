package hu.asztrikx.workout.presentation.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.service.log.LogService
import hu.asztrikx.workout.service.log.Log
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
					_state.update { LogState.Result(logs) }
				}
			} catch (e: Exception) {
				_state.update { LogState.Error(e) }
			}
		}
	}

	fun delete(log: Log) {
		(_state.value as LogState.Result).let { stateValue ->
			viewModelScope.launch {
				try {
					_state.update { LogState.Loading }
					service.delete(log)
					/*_state.update { LogState.Result(stateValue.logs.filter {
						it.id != log.id
					}) }*/
				} catch (e: Exception) {
					_state.update { LogState.Error(e) }
				}
			}
		}
	}
}