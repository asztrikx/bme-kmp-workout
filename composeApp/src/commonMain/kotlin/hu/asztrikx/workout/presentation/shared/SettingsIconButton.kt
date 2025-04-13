package hu.asztrikx.workout.presentation.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun SettingsIconButton(onSettingsClick: () -> Unit) {
	IconButton(onSettingsClick) {
		Icon(Icons.Default.Settings, null)
	}
}