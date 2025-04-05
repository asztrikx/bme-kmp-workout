package hu.asztrikx.workout.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.awt.SystemColor.text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithSettings(text: String, onSettingsClick: () -> Unit) {
	TopAppBar(
		title = {
			Row(
				Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically,
					// IconButton too large
			) {
				Text(text)
				IconButton(
					onClick = onSettingsClick
				) {
					Icon(Icons.Default.Settings, null)
				}
			}
		},
	)
}