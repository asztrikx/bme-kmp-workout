package hu.asztrikx.workout.presentation.ui.categoryEdit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
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
import hu.asztrikx.workout.presentation.mapper.iconsMap
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryEditDialog(
	title: String,
	confirmText: String,
	onDismiss: () -> Unit,
	onSave: () -> Unit,
	id: Long?,
) {
	val viewModel: CategoryEditViewModel = koinViewModel()
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
					iconsMap.entries.forEach { (name, icon) ->
						val backgroundColor = if (state.iconName == name) Color.Blue.copy(alpha = 0.2f) else Color.Transparent
						IconButton(
							{ viewModel.onEvent(CategoryEditEvent.IconName(icon, name)) },
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