package hu.asztrikx.workout.presentation.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
	content: @Composable () -> Unit
) {
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
			content()
			Box(
				Modifier
					.align(Alignment.CenterEnd)
					.padding(end = 16.dp)
			) {
				floatingActionButton()
			}
		}
	}
}