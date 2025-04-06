package hu.asztrikx.workout.logEdit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.category.Category
import hu.asztrikx.workout.log.Log
import hu.asztrikx.workout.quantity.Quantity
import hu.asztrikx.workout.shared.currentDate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class LogEditViewModel: ViewModel() {
	private val _state = MutableStateFlow<LogEditState>(LogEditState.Loading)
	val state = _state.asStateFlow()

	fun new() {
		_state.value = LogEditState.Result(Log(
			-1,
			currentDate(),
			listOf(),
		))
	}

	fun load(id: Int) {
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

		viewModelScope.launch {
			try {
				_state.value = LogEditState.Loading
				delay(0)
				_state.value = LogEditState.Result(Log(
					1,
					LocalDate(2021,1,2),
					listOf(
						Quantity(category1, 10f),
						Quantity(category2, 1.2f),
						Quantity(category2, 1.2f),
					)
				))
			} catch (e: Exception) {
				_state.value = LogEditState.Error(e)
			}
		}
	}

	fun save(log: Log) {
	}
}