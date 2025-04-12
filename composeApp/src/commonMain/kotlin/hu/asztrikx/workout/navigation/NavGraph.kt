package hu.asztrikx.workout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hu.asztrikx.workout.logEdit.screen.LogEditScreen
import hu.asztrikx.workout.log.screen.LogScreen
import hu.asztrikx.workout.settings.screen.SettingsScreen
import hu.asztrikx.workout.stats.screen.StatsScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
	val onAddClick = { navHostController.navigate(Screen.LogAdd.route) }
	val onStatsClick = { navHostController.navigate(Screen.Stats.route) }
	val onSettingsClick = { navHostController.navigate(Screen.Settings.route) }
	val onSLogsClick = { navHostController.navigate(Screen.Log.route) }
	val onBackClick: () -> Unit = { navHostController.popBackStack() }

	NavHost(navController = navHostController, startDestination = Screen.Log.route) {
		composable(Screen.Log.route) {
			LogScreen(
				onAddClick,
				onStatsClick,
				onSettingsClick,
				onEditClick = { navHostController.navigate(Screen.LogEdit.createRoute(it)) },
			)
		}
		composable(Screen.LogAdd.route) {
			LogEditScreen("Add", null, onBackClick)
		}
		composable(Screen.LogEdit.route) { navBackStackEntry ->
			val id = navBackStackEntry.arguments?.getString("id")!!
			LogEditScreen("Edit", id.toInt(), onBackClick)
		}
		composable(Screen.Stats.route) {
			StatsScreen(
				onAddClick,
				onSLogsClick,
				onSettingsClick,
			)
		}
		composable(Screen.Settings.route) {
			SettingsScreen(onBackClick)
		}
	}
}