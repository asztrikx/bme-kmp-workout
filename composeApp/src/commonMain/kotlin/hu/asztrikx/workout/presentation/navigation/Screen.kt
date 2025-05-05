package hu.asztrikx.workout.presentation.navigation

sealed class Screen(val route: String) {
	data object Log: Screen("log")
	data object LogAdd: Screen("logAdd")
	data object LogEdit: Screen("log/{id}") {
		fun createRoute(id: Long) = "log/$id"
	}
	data object Stats: Screen("stats")
	data object Settings: Screen("settings")
}
