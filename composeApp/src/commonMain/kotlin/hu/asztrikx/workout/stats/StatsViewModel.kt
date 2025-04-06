package hu.asztrikx.workout.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatsViewModel: ViewModel() {
	private val _state = MutableStateFlow<StatsState>(StatsState.Loading)
	val state = _state.asStateFlow()

	init {
		load()
	}

	fun load() {
		viewModelScope.launch {
			try {
				_state.value = StatsState.Loading
				delay(0)
				_state.value = StatsState.Result(0)
			} catch (e: Exception) {
				_state.value = StatsState.Error(e)
			}
		}
	}
}