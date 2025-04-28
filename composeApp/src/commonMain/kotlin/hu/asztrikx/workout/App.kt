package hu.asztrikx.workout

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import hu.asztrikx.workout.service.settings.SettingsService
import hu.asztrikx.workout.presentation.navigation.NavGraph
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
	val navHostController = rememberNavController()

	// Database populate
	// 		RoomDatabase.Callback() created circular dependency for injection
	val scope = rememberCoroutineScope()
	val service: SettingsService = koinInject()
	LaunchedEffect(Unit) {
		scope.launch {
			service.initialize()
		}
	}

	MaterialTheme {
		NavGraph(navHostController)
	}
}
