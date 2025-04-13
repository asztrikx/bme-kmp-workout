package hu.asztrikx.workout.presentation.navigation

sealed class Screen(val route: String) {
	data object Log: Screen("log")
	data object LogAdd: Screen("log/add")
	data object LogEdit: Screen("log/{id}") {
		fun createRoute(id: Int) = "log/$id"
	}
	data object Stats: Screen("stats")
	data object Settings: Screen("settings")
}
