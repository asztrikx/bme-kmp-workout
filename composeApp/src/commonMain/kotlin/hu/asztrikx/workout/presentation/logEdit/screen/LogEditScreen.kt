package hu.asztrikx.workout.presentation.logEdit.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.logEdit.LogEditEvent
import hu.asztrikx.workout.presentation.logEdit.LogEditUIEvent
import hu.asztrikx.workout.presentation.logEdit.LogEditViewModel
import hu.asztrikx.workout.presentation.shared.BetterScaffold
import hu.asztrikx.workout.presentation.shared.CustomDatePicker
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogEditScreen(
	text: String,
	id: Long?,
	onBackClick: () -> Unit,
) {
	val viewModel: LogEditViewModel = koinViewModel()
	val log by viewModel.state.collectAsState()

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
				is LogEditUIEvent.Success -> {
					onBackClick()
				}
			}
		}
	}

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = { Text(text) },
				navigationIcon = {
					IconButton(onBackClick) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
					}
				}
			)
		},
		floatingActionButton = {
			FloatingActionButton({ viewModel.onEvent(LogEditEvent.Save) }) {
				if (id == null) {
					Icon(Icons.Default.Save, null)
				} else {
					Icon(Icons.Default.SaveAs, null)
				}
			}
		},
	) {
		Column(
			Modifier.fillMaxSize().padding(20.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			CustomDatePicker(
				log.date,
				{ viewModel.onEvent(LogEditEvent.Date(it)) },
				{ Text("Date of workout") }
			)

			Spacer(Modifier.height(20.dp))

			LazyColumn {
				items(log.quantities, /*key = { it.id }*/) { quantity ->
					LogEditQuantityItem(quantity, { viewModel.onEvent(LogEditEvent.Category(it, quantity.id)) })
				}
			}
		}
	}
}