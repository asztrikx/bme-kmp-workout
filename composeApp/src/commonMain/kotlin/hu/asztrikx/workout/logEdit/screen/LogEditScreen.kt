package hu.asztrikx.workout.logEdit.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import hu.asztrikx.workout.logEdit.LogEditState
import hu.asztrikx.workout.logEdit.LogEditViewModel
import hu.asztrikx.workout.shared.BetterScaffold
import hu.asztrikx.workout.shared.CustomDatePicker
import hu.asztrikx.workout.shared.LoadingScreen
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogEditScreen(navHostController: NavHostController, text: String, id: Int?) {
	val viewModel: LogEditViewModel = koinInject()
	val state = viewModel.state.collectAsState().value
		// Smart cast to 'LogEditState. Result' is impossible, because 'state' is a property that has open or custom getter

	LaunchedEffect(Unit) {
		if (id != null) {
			viewModel.load(id)
		} else {
			viewModel.new()
		}
	}

	BetterScaffold(
		topBar = {
			TopAppBar(
				title = { Text(text) },
				navigationIcon = {
					IconButton(onClick = { navHostController.popBackStack() }) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
					}
				}
			)
		},
		floatingActionButton = {
			FloatingActionButton(onClick = { /*viewModel.save() */}) { // TODO we need state here
				if (id == null) {
					Icon(Icons.Default.Save, null)
				} else {
					Icon(Icons.Default.SaveAs, null)
				}
			}
		},
	) {
		when (state) {
			is LogEditState.Result -> {
				println(state.log)
				println(state.log.quantities)
				println(state.log.quantities.size)
				var date by remember { mutableStateOf(state.log.date) }
				val quantities = remember { state.log.quantities.map { null }.toMutableStateList<Int?>() }
				println(quantities.size)

				Column(
					Modifier.fillMaxSize().padding(20.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					CustomDatePicker(date, { date = it }, { Text("Date of workout") })

					Spacer(Modifier.height(20.dp))

					state.log.quantities.forEachIndexed { index, quantity ->
						LogEditQuantityItem(
							quantity,
							quantities[index],
							{ quantities[index] = it },
						)
					}
				}
			}
			is LogEditState.Loading -> {
				LoadingScreen()
			}
			is LogEditState.Error -> {
				Text("error")
			}
		}
	}
}