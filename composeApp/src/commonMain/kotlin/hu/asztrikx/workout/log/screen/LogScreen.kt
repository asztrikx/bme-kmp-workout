package hu.asztrikx.workout.log.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.log.LogState
import hu.asztrikx.workout.log.LogViewModel
import hu.asztrikx.workout.shared.BetterScaffold
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogScreen() {
	val viewModel: LogViewModel = koinInject()
	val state = viewModel.state.collectAsState().value
		// Smart cast to 'LogState. Result' is impossible, because 'state' is a property that has open or custom getter

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = {
					Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
						Text("Logs")
						IconButton(
							onClick = {}
						) {
							Icon(Icons.Default.Settings, null)
						}
					}
				},
			)
		},
		floatingActionButton = {
			Column {
				FloatingActionButton(
					onClick = {}
				) {
					Icon(Icons.Default.Add, null)
				}
				Spacer(Modifier.height(10.dp))
				FloatingActionButton(
					onClick = {}
				) {
					Icon(Icons.Filled.Timeline, null)
				}
			}
		},
	) {
		when (state) {
			is LogState.Result -> {
				val expanded = remember { state.logs.map { false }.toMutableStateList() }
				val angles = expanded.map { isExpanded ->
					animateFloatAsState(if (isExpanded) 0f else -90f).value // TODO why does this work?
				}

				LazyColumn(
					modifier = Modifier.fillMaxSize().padding(20.dp),
					horizontalAlignment = Alignment.CenterHorizontally,
				) {
					itemsIndexed(state.logs, /* TODO key*/) { index, log ->
						LogItem(
							log,
							expanded[index],
							{ expanded[index] = !expanded[index] },
							angles[index],
							{ viewModel.delete(log) }
						)
						LogItemMore(log, expanded[index])
						Spacer(Modifier.height(20.dp))
					}
				}
			}
			is LogState.Loading -> {
				CircularProgressIndicator(
					color = MaterialTheme.colorScheme.onSecondaryContainer
				)
			}
			is LogState.Error -> {
				Text(state.error.toString())
			}
		}
	}
}
