package hu.asztrikx.workout.settings.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryAddDialog(
	onDismiss: () -> Unit,
	onAdd: () -> Unit
) {
	var newCategoryName by remember { mutableStateOf("") }
	var newCategoryUnit by remember { mutableStateOf("") }
	var newCategoryIcon by remember { mutableStateOf(Icons.Default.FitnessCenter) }

	AlertDialog(
		title = {
			Text("Add category")
		},
		text = {
			Column {
				Text("Name", style = MaterialTheme.typography.labelMedium)
				OutlinedTextField(
					newCategoryName,
					{ newCategoryName = it },
				)

				Spacer(Modifier.height(20.dp))

				Text("Unit", style = MaterialTheme.typography.labelMedium)
				OutlinedTextField(
					newCategoryUnit,
					{ newCategoryUnit = it },
				)

				Spacer(Modifier.height(20.dp))

				Text("Icon", style = MaterialTheme.typography.labelMedium)

				FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
					List(15) {
						if (it % 2 == 0) Icons.Default.FitnessCenter else Icons.AutoMirrored.Filled.DirectionsRun
					}.forEach { icon ->
						val backgroundColor = if (newCategoryIcon == icon) Color.Blue.copy(alpha = 0.2f) else Color.Transparent
						IconButton({ newCategoryIcon = icon }, modifier = Modifier.background(color = backgroundColor, shape = CircleShape)) {
							Icon(icon, null)
						}
					}
				}
			}
		},
		onDismissRequest = { onDismiss() },
		confirmButton = {
			TextButton({
				onAdd()
				onDismiss()
			}) {
				Text("Add")
			}
		},
		dismissButton = {
			TextButton(onDismiss) {
				Text("Cancel")
			}
		}
	)
}