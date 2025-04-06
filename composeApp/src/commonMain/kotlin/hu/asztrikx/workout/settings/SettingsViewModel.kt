package hu.asztrikx.workout.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.category.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class SettingsViewModel: ViewModel() {
	private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
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
				_state.value = SettingsState.Loading
				delay(0)
				_state.value = SettingsState.Result(Settings(
					LocalDate(1998,1,2),
					listOf(
						category1,
						category2,
					)
				))
			} catch (e: Exception) {
				_state.value = SettingsState.Error(e)
			}
		}
	}
}