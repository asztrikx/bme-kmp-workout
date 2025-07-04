package hu.asztrikx.workout.presentation.ui.log.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.mapper.LogUI
import hu.asztrikx.workout.presentation.ui.log.LogViewModel
import hu.asztrikx.workout.presentation.ui.shared.format
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LogItem(
	log: LogUI,
	onEdit: () -> Unit,
	onDelete: () -> Unit,
	colors: CardColors = CardDefaults.cardColors(),
	viewModel: LogViewModel = koinViewModel(),
) {
	val expanded = log.expanded
	val angle by animateFloatAsState(if (expanded) 0f else -90f)

	Card(
		onClick = { viewModel.changeExpanded(log) },
		colors = colors,
	) {
		Row(
			modifier = Modifier.padding(20.dp, 10.dp).fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(log.date.format(), style = MaterialTheme.typography.titleMedium)

			Row(verticalAlignment = Alignment.CenterVertically) {
				AnimatedVisibility(
					!expanded,
					enter = slideInHorizontally(initialOffsetX = { 2*it }) + expandHorizontally() + fadeIn(),
					exit = slideOutHorizontally(targetOffsetX = { 2*it }) + shrinkHorizontally() + fadeOut()
				) {
					Row(verticalAlignment = Alignment.CenterVertically) {
						log.quantities.forEach {
							if (it.count == null) return@forEach
							Icon(it.category.icon, null)
							Spacer(Modifier.width(10.dp))
						}
					}
				}
				AnimatedVisibility(
					expanded,
					enter = slideInHorizontally(initialOffsetX = { 2*it }) + expandHorizontally() + fadeIn(),
					exit = slideOutHorizontally(targetOffsetX = { 2*it }) + shrinkHorizontally() + fadeOut()
				) {
					Row(verticalAlignment = Alignment.CenterVertically) {
						IconButton(onDelete) {
							Icon(Icons.Default.Delete, null)
						}
						IconButton(onEdit) {
							Icon(Icons.Default.Edit, null)
						}
					}
				}

				IconButton(
					onClick = { viewModel.changeExpanded(log) },
					Modifier.rotate(degrees = angle),
				) {
					Icon(Icons.Default.ArrowDropDown, null)
				}
			}
		}
	}

	LogItemMore(log, expanded)
}