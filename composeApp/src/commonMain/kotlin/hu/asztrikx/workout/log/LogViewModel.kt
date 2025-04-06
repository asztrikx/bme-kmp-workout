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
				Icons.AutoMirrored.Filled.DirectionsRun,
				"Running",
				"km",
			)
			val category2 = Category(
				Icons.Default.FitnessCenter,
				"Lifting",
				"db",
			)
			val model1 = Log(1,
				date = LocalDate(2025, 1, 2),
				quantities = listOf(
					Quantity(category1, 10f),
					Quantity(category2, 1.2f),
					Quantity(category2, 1.2f),
				)
			)
			val model2 = Log(2,
				date = LocalDate(2025, 1, 2),
				quantities = listOf(
					Quantity(category1, 100f),
				)
			)

			try {
				_state.value = LogState.Loading
				delay(0)
				_state.value = LogState.Result(List(15) {
					if (it % 2 == 0) model1 else model2
				})
			} catch (e: Exception) {
				_state.value = LogState.Error(e)
			}
		}
	}

	fun delete(log: Log) {
		(_state.value as LogState.Result).let { stateValue ->
			viewModelScope.launch {
				try {
					_state.value = LogState.Loading
					delay(0)
					_state.value = LogState.Result(stateValue.logs.filter {
						it.id != log.id
					})
				} catch (e: Exception) {
					_state.value = LogState.Error(e)
				}
			}
		}
	}
}