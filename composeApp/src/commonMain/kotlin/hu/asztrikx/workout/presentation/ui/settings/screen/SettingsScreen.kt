package hu.asztrikx.workout.presentation.ui.settings.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.ui.settings.SettingsEvent
import hu.asztrikx.workout.presentation.ui.settings.SettingsState
import hu.asztrikx.workout.presentation.ui.categoryEdit.CategoryEditDialog
import hu.asztrikx.workout.presentation.ui.settings.SettingsViewModel
import hu.asztrikx.workout.presentation.ui.shared.BetterScaffold
import hu.asztrikx.workout.presentation.ui.shared.CustomDatePicker
import hu.asztrikx.workout.presentation.ui.shared.LoadingScreen
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
	onBackClick: () -> Unit,
) {
	val viewModel: SettingsViewModel = koinViewModel()
	val state = viewModel.state.collectAsState().value
		// Smart cast to 'SettingsState. Result' is impossible, because 'state' is a property that has open or custom getter

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = { Text("Settings") },
				navigationIcon = {
					IconButton(onBackClick) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
					}
				}
			)
		},
	) {
		when (state) {
			is SettingsState.Result -> {
				Column(Modifier.padding(20.dp)) {
					Text("Start of plot range", style = MaterialTheme.typography.labelMedium)
					CustomDatePicker(
						state.settings.startDate,
						{ viewModel.onEvent(SettingsEvent.DateChange(it)) },
						{}
					)

					Spacer(Modifier.height(30.dp))

					Text("Export data", style = MaterialTheme.typography.labelMedium)
					Row(Modifier.fillMaxWidth().padding(20.dp, 0.dp), horizontalArrangement = Arrangement.Center) {
						Button({ }) {
							Text("Export")
						}
					}

					Spacer(Modifier.height(30.dp))

					Text("Categories of workout", style = MaterialTheme.typography.labelMedium)

					var categoryAddShow by remember { mutableStateOf(false) }
					Row(Modifier.fillMaxWidth().padding(0.dp, 20.dp), horizontalArrangement = Arrangement.Center) {
						Button({ categoryAddShow = true }) {
							Text("Create new")
						}
					}
					if (categoryAddShow) {
						CategoryEditDialog(
							"Add category",
							"Add",
							{ categoryAddShow = !categoryAddShow },
							{ categoryAddShow = !categoryAddShow },
							null,
						)
					}

					LazyColumn(
						modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp),
						horizontalAlignment = Alignment.CenterHorizontally,
					) {
						items(state.settings.categories, key = { it.id }) { category ->
							SettingsScreenItem(category)
						}
					}

				}
			}
			is SettingsState.Loading -> {
				LoadingScreen()
			}
			is SettingsState.Error -> {
				Text(state.error.toString())
			}
		}
	}
}