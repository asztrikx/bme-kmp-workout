package hu.asztrikx.workout.stats.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.shared.BetterScaffold
import hu.asztrikx.workout.shared.CustomDatePicker
import hu.asztrikx.workout.shared.LoadingScreen
import hu.asztrikx.workout.shared.SettingsIconButton
import hu.asztrikx.workout.stats.StatsEvent
import hu.asztrikx.workout.stats.StatsState
import hu.asztrikx.workout.stats.StatsViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
	onAddClick: () -> Unit,
	onLogsClick: () -> Unit,
	onSettingsClick: () -> Unit,
) {
	val viewModel: StatsViewModel = koinInject()
	val state by viewModel.state.collectAsState()

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = { Text("Stats") },
				actions = { SettingsIconButton(onSettingsClick) },
			)
		 },
		floatingActionButton = {
			Column {
				FloatingActionButton(onAddClick) {
					Icon(Icons.Default.Add, null)
				}
				Spacer(Modifier.height(10.dp))
				FloatingActionButton(onLogsClick) {
					Icon(Icons.Filled.Schedule, null)
				}
			}
		},
	) {
		when (val state = state) {
			is StatsState.Result -> {
				Column(
					Modifier.fillMaxSize().padding(20.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					CustomDatePicker(
						state.startDate,
						{ viewModel.onEvent(StatsEvent.StartDate(it)) },
						{ Text("Start of range") }
					)

					LazyColumn(Modifier.fillMaxSize()) {
						items(state.categories.zip(state.quantityWithDates())) { (category, quantityWithDates) ->
							Spacer(Modifier.height(40.dp))
							StatsItem(category, quantityWithDates)
						}
					}
				}
			}
			is StatsState.Loading -> {
				LoadingScreen()
			}
			is StatsState.Error -> {
				Text("error")
			}
		}
	}
}