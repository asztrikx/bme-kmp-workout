package hu.asztrikx.workout.presentation.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.asztrikx.workout.model.Category
import hu.asztrikx.workout.model.Settings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class SettingsViewModel: ViewModel() {
	private val _state = MutableStateFlow<hu.asztrikx.workout.presentation.settings.SettingsState>(
		hu.asztrikx.workout.presentation.settings.SettingsState.Loading
	)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
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

		viewModelScope.launch {
			try {
				_state.value =
					hu.asztrikx.workout.presentation.settings.SettingsState.Loading // update
				delay(0)
				_state.value = hu.asztrikx.workout.presentation.settings.SettingsState.Result(
					Settings(
						LocalDate(1998, 1, 2),
						listOf(
							category1,
							category2,
						)
					)
				)
			} catch (e: Exception) {
				_state.value = hu.asztrikx.workout.presentation.settings.SettingsState.Error(e)
			}
		}
	}
}