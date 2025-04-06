package hu.asztrikx.workout.stats.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Schema
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hu.asztrikx.workout.navigation.Screen
import hu.asztrikx.workout.shared.BetterScaffold
import hu.asztrikx.workout.shared.CustomDatePicker
import hu.asztrikx.workout.shared.LoadingScreen
import hu.asztrikx.workout.shared.TopAppBarWithSettings
import hu.asztrikx.workout.stats.StatsState
import hu.asztrikx.workout.stats.StatsViewModel
import kotlinx.datetime.LocalDate
import org.koin.compose.koinInject

@Composable
fun StatsScreen(navHostController: NavHostController) {
	val viewModel: StatsViewModel = koinInject()
	val state by viewModel.state.collectAsState()

	BetterScaffold(
		topBar = { TopAppBarWithSettings("Stats", navHostController) },
		floatingActionButton = {
			Column {
				FloatingActionButton(
					onClick = { navHostController.navigate(Screen.Log.route)}
				) {
					Icon(Icons.Filled.Schedule, null)
				}
				Spacer(Modifier.height(10.dp))
				FloatingActionButton(
					onClick = { navHostController.navigate(Screen.LogAdd.route) }
				) {
					Icon(Icons.Default.Add, null)
				}
			}
		},
	) {
		when (state) {
			is StatsState.Result -> {
				var date by remember { mutableStateOf<LocalDate?>(null) }

				Column(
					Modifier.fillMaxSize().padding(20.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					CustomDatePicker(date, { date = it }, { Text("Start of range") })

					Spacer(Modifier.height(20.dp))


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