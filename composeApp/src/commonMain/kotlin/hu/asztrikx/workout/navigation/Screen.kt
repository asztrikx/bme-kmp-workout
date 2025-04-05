package hu.asztrikx.workout.navigation

sealed class Screen(val route: String) {
	data object Log: Screen("log")
	data object LogItem: Screen("log/{id}") {
		fun createRoute(id: Int) = "log/${id}"
	}
	data object Stats: Screen("stats")
	data object Settings: Screen("settings")
}
