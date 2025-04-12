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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class LogEditViewModel: ViewModel() {
	private val _state = MutableStateFlow(Log(-1, currentDate(), listOf()))
	val state = _state.asStateFlow()

	private val _uiEvent = Channel<LogEditUIEvent>()
	val uiEvent = _uiEvent.receiveAsFlow()

	fun new() {
		_state.update { Log(-1, currentDate(), listOf()) }
	}

	fun edit(id: Int) {
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

		_state.update { Log(
			1,
			LocalDate(2021,1,2),
			listOf(
				Quantity(1, category1, 10f),
				Quantity(2, category2, 1.2f),
				Quantity(3, category2, 1.2f),
			)
		) }
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
				// TODO lementés (viewModelScope), fő screen valahogy frissüljön majd
				viewModelScope.launch {
					_uiEvent.send(LogEditUIEvent.Success)
				}
			}
		}
	}
}