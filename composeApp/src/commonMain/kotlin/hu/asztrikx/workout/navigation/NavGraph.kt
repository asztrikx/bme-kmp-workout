package hu.asztrikx.workout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hu.asztrikx.workout.logEdit.screen.LogEditScreen
import hu.asztrikx.workout.log.screen.LogScreen

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

		}
		composable(Screen.Settings.route) {

		}
	}
}