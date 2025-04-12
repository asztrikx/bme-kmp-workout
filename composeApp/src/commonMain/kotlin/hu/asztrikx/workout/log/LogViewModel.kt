package hu.asztrikx.workout.log

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.category.Category
import hu.asztrikx.workout.quantity.Quantity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class LogViewModel: ViewModel() {
	private val _state = MutableStateFlow<LogState>(LogState.Loading)
	val state = _state.asStateFlow()

	init {
		loadLogs()
	}

	private fun loadLogs() {
		viewModelScope.launch {
			val category1 = Category(
				1,
				Icons.AutoMirrored.Filled.DirectionsRun,
				"Running",
				"km",
			)
			val category2 = Category(
				2,
				Icons.Default.FitnessCenter,
				"Lifting",
				"db",
			)
			val model1 = Log(1,
				date = LocalDate(2025, 1, 2),
				quantities = listOf(
					Quantity(1, category1, 10f),
					Quantity(2, category2, 1.2f),
					Quantity(3, category2, 1.2f),
				)
			)
			val model2 = Log(2,
				date = LocalDate(2025, 1, 2),
				quantities = listOf(
					Quantity(4, category1, 100f),
				)
			)

			try {
				_state.update { LogState.Loading }
				delay(0)
				_state.update { LogState.Result(List(15) {
					if (it % 2 == 0) model1.copy(id = it) else model2.copy(id = it)
				}) }
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
					delay(0)
					_state.update { LogState.Result(stateValue.logs.filter {
						it.id != log.id
					}) }
				} catch (e: Exception) {
					_state.update { LogState.Error(e) }
				}
			}
		}
	}
}