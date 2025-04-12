package hu.asztrikx.workout.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import hu.asztrikx.workout.navigation.Screen

@Composable
fun SettingsIconButton(navHostController: NavHostController) {
	IconButton(
		onClick = { navHostController.navigate(Screen.Settings.route) }
	) {
		Icon(Icons.Default.Settings, null)
	}
}