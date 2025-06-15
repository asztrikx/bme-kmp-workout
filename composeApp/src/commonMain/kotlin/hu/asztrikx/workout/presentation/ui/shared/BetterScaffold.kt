package hu.asztrikx.workout.presentation.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.ui.settings.SettingsState
import hu.asztrikx.workout.presentation.ui.settings.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BetterScaffold(
	modifier: Modifier = Modifier,
	topBar: @Composable () -> Unit = {},
	bottomBar: @Composable () -> Unit = {},
	snackbarHost: @Composable () -> Unit = {},
	floatingActionButton: @Composable () -> Unit = {},
	containerColor: Color = MaterialTheme.colorScheme.background,
	contentColor: Color = contentColorFor(containerColor),
	contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
	settingsViewModel: SettingsViewModel = koinViewModel(),
	content: @Composable () -> Unit
) {
	val settingsState by settingsViewModel.state.collectAsState()

	Scaffold(
		modifier,
		topBar,
		bottomBar,
		snackbarHost,
		{},
		FabPosition.End,
		containerColor,
		contentColor,
		contentWindowInsets,
	) { paddingValues ->
		Box(Modifier.padding(paddingValues).fillMaxSize()) {
			Box(
				modifier = Modifier
					.align(Alignment.Center)
					.widthIn(max = 800.dp)
					.fillMaxHeight()
			) {
				content()
			}

			when (settingsState) {
				is SettingsState.Result -> {
					val modifier = if ((settingsState as SettingsState.Result).settings.leftHanded) {
						Modifier
							.align(Alignment.CenterStart)
							.padding(start = 16.dp)
					} else {
						Modifier
							.align(Alignment.CenterEnd)
							.padding(end = 16.dp)
					}

					Box(modifier) {
						floatingActionButton()
					}
				}
				else -> {}
			}
		}
	}
}