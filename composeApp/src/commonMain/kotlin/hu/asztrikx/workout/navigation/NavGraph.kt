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
	NavHost(navController = navHostController, startDestination = Screen.Log.route) {
		composable(Screen.Log.route) {
			LogScreen(navHostController)
		}
		composable(Screen.LogAdd.route) {
			LogEditScreen(navHostController, "Add", null)
		}
		composable(Screen.LogEdit.route) { navBackStackEntry ->
			val id = navBackStackEntry.arguments?.getString("id")!!
			LogEditScreen(navHostController, "Edit", id.toInt())
		}
		composable(Screen.Stats.route) {
			StatsScreen(navHostController)
		}
		composable(Screen.Settings.route) {
			SettingsScreen(navHostController)
		}
	}
}