package hu.asztrikx.workout.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

fun convertMillisToDate(millis: Long): LocalDate {
	val instant = Instant.fromEpochMilliseconds(millis)
	val timeZone = TimeZone.currentSystemDefault()
	val localDateTime = instant.toLocalDateTime(timeZone)
	return localDateTime.date
}

fun currentDate(): LocalDate {
	val now = Clock.System.now()
	val timeZone = TimeZone.currentSystemDefault()
	val localDateTime = now.toLocalDateTime(timeZone)
	return localDateTime.date
}

fun LocalDate.format(): String {
	val format = LocalDate.Format {
		year()
		char('-')
		monthNumber()
		char('-')
		dayOfMonth()
	}

	return format.format(this)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
	date: LocalDate?,
	onDateSelected: (LocalDate) -> Unit,
	label: @Composable (() -> Unit),
) {
	val datePickerState = rememberDatePickerState()
	var dismiss by remember { mutableStateOf(true) }

	OutlinedTextField(
		value = date?.format() ?: "",
		onValueChange = {},
		label = label,
		trailingIcon = {
			IconButton({ dismiss = false }) {
				Icon(Icons.Default.DateRange, null)
			}
		},
		modifier = Modifier.fillMaxWidth().clickable { dismiss = false }
	)

	val onDismiss = {
		dismiss = true
	}

	if (!dismiss) {
		DatePickerDialog(
			onDismissRequest = onDismiss,
			confirmButton = {
				TextButton(onClick = {
					onDateSelected(convertMillisToDate(datePickerState.selectedDateMillis!!))
					onDismiss()
				}) {
					Text("OK")
				}
			},
			dismissButton = {
				TextButton(onClick = onDismiss) {
					Text("Cancel")
				}
			}
		) {
			DatePicker(state = datePickerState)
		}
	}
}