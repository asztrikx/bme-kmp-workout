package hu.asztrikx.workout.settings.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOff
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import hu.asztrikx.workout.navigation.Screen
import hu.asztrikx.workout.settings.SettingsState
import hu.asztrikx.workout.settings.SettingsViewModel
import hu.asztrikx.workout.shared.BetterScaffold
import hu.asztrikx.workout.shared.CustomDatePicker
import hu.asztrikx.workout.shared.LoadingScreen
import hu.asztrikx.workout.shared.TopAppBarWithSettings
import org.koin.compose.koinInject
import java.awt.SystemColor.text
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SettingsScreen(navHostController: NavHostController) {
	val viewModel: SettingsViewModel = koinInject()
	val state = viewModel.state.collectAsState().value
		// Smart cast to 'SettingsState. Result' is impossible, because 'state' is a property that has open or custom getter

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = { Text("Settings") },
				navigationIcon = {
					IconButton({ navHostController.popBackStack() }) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
					}
				}
			)
		},
	) {
		when (state) {
			is SettingsState.Result -> {
				Column(Modifier.padding(20.dp)) {
					var startDate by remember { mutableStateOf(state.settings.startDate) }
					Text("Start of plot range", style = MaterialTheme.typography.labelMedium)
					CustomDatePicker(startDate, { startDate = it }, { })

					Spacer(Modifier.height(30.dp))

					var localsaveChecked by remember { mutableStateOf(true) }
					Text("Enable auto sync to local storage", style = MaterialTheme.typography.labelMedium)
					Row(
						Modifier.fillMaxWidth().padding(20.dp, 0.dp),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween
					) {
						Text("Save to device also")
						Switch(
							checked = localsaveChecked,
							onCheckedChange = {
								localsaveChecked = it
							}
						)
					}
					if (localsaveChecked) {
						Row(Modifier.fillMaxWidth().padding(20.dp, 0.dp), horizontalArrangement = Arrangement.Center) {
							Button({ }) {
								Text("View directory")
							}
						}
					}

					Spacer(Modifier.height(30.dp))

					Text("Categories of workout", style = MaterialTheme.typography.labelMedium)

					var categoryAddShow by remember { mutableStateOf(false) }
					Row(Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Center) {
						Button({ categoryAddShow = true }) {
							Text("Create new")
						}
					}
					if (categoryAddShow) {
						CategoryAddDialog({ categoryAddShow = !categoryAddShow }, { })
					}

					LazyColumn(
						modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp),
						horizontalAlignment = Alignment.CenterHorizontally,
					) {
						itemsIndexed(state.settings.categories, /* TODO key*/) { index, category ->
							Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
								Icon(category.icon, null)
								Text(category.name)
								Text(category.unit)
							}
							Spacer(Modifier.height(20.dp))
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