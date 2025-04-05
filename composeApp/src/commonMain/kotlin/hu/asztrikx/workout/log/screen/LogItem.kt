package hu.asztrikx.workout.log.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.log.Log
import hu.asztrikx.workout.log.LogViewModel
import org.koin.compose.koinInject

@Composable
fun LogItem(log: Log, isExpanded: Boolean, onExpandedChange: () -> Unit, angle: Float, onDelete: () -> Unit) {
	Card(Modifier.fillMaxWidth().clickable { onExpandedChange() }) {
		Row(
			modifier = Modifier.padding(20.dp, 10.dp).fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(log.dateTime.date.toString(), style = MaterialTheme.typography.titleMedium)

			Row(verticalAlignment = Alignment.CenterVertically) {
				if (!isExpanded) {
					log.quantities.forEach {
						Icon(it.category.icon, null)
						Spacer(Modifier.width(10.dp))
					}
				} else {
					IconButton(
						onClick = { onDelete() }
					) {
						Icon(Icons.Default.Delete, null)
					}
					IconButton(
						onClick = {}
					) {
						Icon(Icons.Default.Edit, null)
					}
				}

				IconButton(
					onClick = { onExpandedChange() },
					Modifier.rotate(degrees = angle),
				) {
					Icon(Icons.Default.ArrowDropDown, null)
				}
			}
		}
	}
}