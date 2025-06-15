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
import androidx.compose.material3.Switch
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
import hu.asztrikx.workout.presentation.ui.categoryEdit.screen.CategoryEditDialog
import hu.asztrikx.workout.presentation.ui.categoryEdit.screen.CategoryEditItem
import hu.asztrikx.workout.presentation.ui.settings.SettingsViewModel
import hu.asztrikx.workout.presentation.ui.shared.BetterScaffold
import hu.asztrikx.workout.presentation.ui.shared.CustomDatePicker
import hu.asztrikx.workout.presentation.ui.shared.ErrorScreen
import hu.asztrikx.workout.presentation.ui.shared.LoadingScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import workout.composeapp.generated.resources.Res
import workout.composeapp.generated.resources.add
import workout.composeapp.generated.resources.addCategory
import workout.composeapp.generated.resources.categoriesOfWorkout
import workout.composeapp.generated.resources.createNew
import workout.composeapp.generated.resources.export
import workout.composeapp.generated.resources.exportData
import workout.composeapp.generated.resources.leftHandedModeSection
import workout.composeapp.generated.resources.leftHandedModeToggle
import workout.composeapp.generated.resources.settings
import workout.composeapp.generated.resources.startOfPlotRange

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(
	onBackClick: () -> Unit,
	viewModel: SettingsViewModel = koinViewModel(),
) {
	val state = viewModel.state.collectAsState().value
		// Smart cast to 'SettingsState. Result' is impossible, because 'state' is a property that has open or custom getter

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = { Text(stringResource(Res.string.settings)) },
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
					Text(stringResource(Res.string.startOfPlotRange), style = MaterialTheme.typography.labelMedium)
					CustomDatePicker(
						state.settings.startDate,
						{ viewModel.onEvent(SettingsEvent.DateChange(it)) },
						{}
					)

					Spacer(Modifier.height(30.dp))

					Text(stringResource(Res.string.leftHandedModeSection), style = MaterialTheme.typography.labelMedium)
					Row(Modifier.fillMaxWidth().padding(20.dp, 0.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
						Text(stringResource(Res.string.leftHandedModeToggle), Modifier.align(Alignment.CenterVertically))
						Switch(
							checked = state.settings.leftHanded,
							onCheckedChange = { viewModel.onEvent(SettingsEvent.LeftHandedChange(it)) }
						)
					}

					Spacer(Modifier.height(30.dp))

					Text(stringResource(Res.string.exportData), style = MaterialTheme.typography.labelMedium)
					Row(Modifier.fillMaxWidth().padding(20.dp, 0.dp), horizontalArrangement = Arrangement.Center) {
						Button({ viewModel.export() }) {
							Text(stringResource(Res.string.export))
						}
					}

					Spacer(Modifier.height(30.dp))

					Text(stringResource(Res.string.categoriesOfWorkout), style = MaterialTheme.typography.labelMedium)

					var categoryAddShow by remember { mutableStateOf(false) }
					Row(Modifier.fillMaxWidth().padding(0.dp, 20.dp), horizontalArrangement = Arrangement.Center) {
						Button({ categoryAddShow = true }) {
							Text(stringResource(Res.string.createNew))
						}
					}
					if (categoryAddShow) {
						CategoryEditDialog(
							stringResource(Res.string.addCategory),
							stringResource(Res.string.add),
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
							CategoryEditItem(category)
						}
					}

				}
			}
			is SettingsState.Loading -> {
				LoadingScreen()
			}
			is SettingsState.Error -> {
				ErrorScreen(state.error.toString())
			}
		}
	}
}