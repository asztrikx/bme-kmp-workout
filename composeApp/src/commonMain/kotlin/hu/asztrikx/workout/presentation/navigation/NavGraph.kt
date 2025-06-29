package hu.asztrikx.workout.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hu.asztrikx.workout.presentation.ui.logEdit.screen.LogEditScreen
import hu.asztrikx.workout.presentation.ui.log.screen.LogScreen
import hu.asztrikx.workout.presentation.ui.settings.screen.SettingsScreen
import hu.asztrikx.workout.presentation.ui.stats.screen.StatsScreen
import org.jetbrains.compose.resources.stringResource
import workout.composeapp.generated.resources.Res
import workout.composeapp.generated.resources.add
import workout.composeapp.generated.resources.edit

@Composable
fun NavGraph(navHostController: NavHostController) {
	val onAddClick = { navHostController.navigate(Screen.LogAdd.route) }
	val onStatsClick = { navHostController.navigateAfterPop(Screen.Stats, Screen.Log) }
	val onSettingsClick = { navHostController.navigate(Screen.Settings.route) }
	val onLogsClick = { navHostController.navigateAfterPop(Screen.Log, Screen.Stats) }
	val onBackClick: () -> Unit = { navHostController.popBackStack() }

	NavHost(
		navController = navHostController,
		startDestination = Screen.Log.route,
	) {
		composable(Screen.Log.route) {
			LogScreen(
				onAddClick,
				onStatsClick,
				onSettingsClick,
				onEditClick = { navHostController.navigate(Screen.LogEdit.createRoute(it)) },
			)
		}
		composable(Screen.LogAdd.route) {
			LogEditScreen(stringResource(Res.string.add), null, onBackClick)
		}
		composable(Screen.LogEdit.route) { navBackStackEntry ->
			val id = navBackStackEntry.arguments?.getString("id")!!
			LogEditScreen(stringResource(Res.string.edit), id.toLong(), onBackClick)
		}
		composable(Screen.Stats.route) {
			StatsScreen(
				onAddClick,
				onLogsClick,
				onSettingsClick,
			)
		}
		composable(Screen.Settings.route) {
			SettingsScreen(onBackClick)
		}
	}
}

fun NavHostController.navigateAfterPop(
	toRoute: Screen,
	fromRoute: Screen,
) =
	navigate(toRoute.route) {
		popUpTo(fromRoute.route) {
			inclusive = true
			saveState = true
		}
		restoreState = true
	}
