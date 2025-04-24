package hu.asztrikx.workout.presentation.settings.categoryEdit

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryEditDialog(
	title: String,
	confirmText: String,
	onDismiss: () -> Unit,
	onSave: () -> Unit,
	id: Long?,
) {
	val viewModel: CategoryEditViewModel = koinInject()
	val state by viewModel.state.collectAsState()

	LaunchedEffect(Unit) {
		if (id != null) {
			viewModel.edit(id)
		} else {
			viewModel.new()
		}
	}

	LaunchedEffect(Unit) {
		viewModel.uiEvent.collect { uiEvent ->
			when (uiEvent) {
				is CategoryEditUIEvent.Success -> {
					onSave()
				}
			}
		}
	}

	AlertDialog(
		title = {
			Text(title)
		},
		text = {
			Column {
				Text("Name", style = MaterialTheme.typography.labelMedium)
				OutlinedTextField(
					state.name,
					{ viewModel.onEvent(CategoryEditEvent.Name(it)) },
				)

				Spacer(Modifier.height(20.dp))

				Text("Unit", style = MaterialTheme.typography.labelMedium)
				OutlinedTextField(
					state.unit,
					{ viewModel.onEvent(CategoryEditEvent.Unit(it)) },
				)

				Spacer(Modifier.height(20.dp))

				Text("Icon", style = MaterialTheme.typography.labelMedium)

				FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
					List(15) {
						if (it % 2 == 0) Icons.Default.FitnessCenter else Icons.AutoMirrored.Filled.DirectionsRun
					}.forEach { icon ->
						val backgroundColor = if (state.icon == icon) Color.Blue.copy(alpha = 0.2f) else Color.Transparent
						IconButton(
							{ viewModel.onEvent(CategoryEditEvent.Icon(icon)) },
							modifier = Modifier.background(color = backgroundColor, shape = CircleShape)
						) {
							Icon(icon, null)
						}
					}
				}
			}
		},
		onDismissRequest = { onDismiss() },
		confirmButton = {
			TextButton({
				viewModel.onEvent(CategoryEditEvent.Save)
				onSave()
				onDismiss()
			}) {
				Text(confirmText)
			}
		},
		dismissButton = {
			TextButton(onDismiss) {
				Text("Cancel")
			}
		}
	)
}