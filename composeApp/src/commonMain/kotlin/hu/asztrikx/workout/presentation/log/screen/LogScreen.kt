package hu.asztrikx.workout.presentation.log.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.log.LogState
import hu.asztrikx.workout.presentation.log.LogViewModel
import hu.asztrikx.workout.presentation.shared.BetterScaffold
import hu.asztrikx.workout.presentation.shared.LoadingScreen
import hu.asztrikx.workout.presentation.shared.SettingsIconButton
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen(
	onAddClick: () -> Unit,
	onStatsClick: () -> Unit,
	onSettingsClick: () -> Unit,
	onEditClick: (Long) -> Unit,
) {
	val viewModel: LogViewModel = koinInject()
	val state = viewModel.state.collectAsState().value
		// Smart cast to 'LogState. Result' is impossible, because 'state' is a property that has open or custom getter

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = { Text("Log") },
				actions = { SettingsIconButton(onSettingsClick) },
			)
		},
		floatingActionButton = {
			Column {
				FloatingActionButton(onAddClick) {
					Icon(Icons.Default.Add, null)
				}
				Spacer(Modifier.height(10.dp))
				FloatingActionButton(onStatsClick) {
					Icon(Icons.Filled.Timeline, null)
				}
			}
		},
	) {
		when (state) {
			is LogState.Result -> {
				LazyColumn(
					modifier = Modifier.fillMaxSize().padding(20.dp),
					horizontalAlignment = Alignment.CenterHorizontally,
				) {
					itemsIndexed(state.logs, key = { _, it -> it.id }) { index, log ->
						LogItem(
							log,
							{ onEditClick(log.id) },
							{ viewModel.delete(log) },
							if (index % 2 != 0)
								CardDefaults.cardColors(
									containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
								)
							else
								CardDefaults.cardColors()
						)
						Spacer(Modifier.height(20.dp))
					}
				}
			}
			is LogState.Loading -> {
				LoadingScreen()
			}
			is LogState.Error -> {
				Text(state.error.toString())
			}
		}
	}
}
