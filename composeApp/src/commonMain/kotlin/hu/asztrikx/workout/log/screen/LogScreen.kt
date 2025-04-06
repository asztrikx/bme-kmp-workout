package hu.asztrikx.workout.log.screen

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hu.asztrikx.workout.log.LogState
import hu.asztrikx.workout.log.LogViewModel
import hu.asztrikx.workout.navigation.Screen
import hu.asztrikx.workout.shared.BetterScaffold
import hu.asztrikx.workout.shared.TopAppBarWithSettings
import org.koin.compose.koinInject

@Composable
fun LogScreen(navHostController: NavHostController) {
	val viewModel: LogViewModel = koinInject()
	val state = viewModel.state.collectAsState().value
		// Smart cast to 'LogState. Result' is impossible, because 'state' is a property that has open or custom getter

	BetterScaffold(
		topBar = { TopAppBarWithSettings("Log", navHostController) },
		floatingActionButton = {
			Column {
				FloatingActionButton(
					onClick = { navHostController.navigate(Screen.LogAdd.route) }
				) {
					Icon(Icons.Default.Add, null)
				}
				Spacer(Modifier.height(10.dp))
				FloatingActionButton(
					onClick = { navHostController.navigate(Screen.Stats.route)}
				) {
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
					itemsIndexed(state.logs, /* TODO key*/) { index, log ->
						LogItem(
							log,
							{ navHostController.navigate(Screen.LogEdit.createRoute(log.id)) },
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
