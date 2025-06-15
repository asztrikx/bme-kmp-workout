package hu.asztrikx.workout.presentation.ui.stats.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.mapper.CategoryUI
import hu.asztrikx.workout.presentation.ui.settings.SettingsState
import hu.asztrikx.workout.presentation.ui.settings.SettingsViewModel
import hu.asztrikx.workout.presentation.ui.shared.format
import hu.asztrikx.workout.service.stats.QuantityWithDate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StatsItem(
	category: CategoryUI,
	quantityWithDates: List<QuantityWithDate>,
	settingsViewModel: SettingsViewModel = koinViewModel(),
) {
	val settingsState by settingsViewModel.state.collectAsState()

	Column {
		Row(verticalAlignment = Alignment.CenterVertically) {
			Icon(category.icon, null, modifier = Modifier.size(40.dp))
			Spacer(Modifier.width(10.dp))
			Text(category.name, style = MaterialTheme.typography.titleLarge)
		}
		Spacer(Modifier.height(10.dp))
		if (quantityWithDates.size >= 2) {

			val modifier = when (settingsState) {
				is SettingsState.Result -> {
					if((settingsState as SettingsState.Result).settings.leftHanded) {
						Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)
					} else {
						Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)
					}
				}
				else -> {
					Modifier
				}
			}

			Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
				Box(Modifier.widthIn(max = 400.dp)) {
					Chart(category, quantityWithDates)
				}
			}
		} else if (quantityWithDates.size == 1) {
			Text("${quantityWithDates.first().date.format()} ${quantityWithDates.first().count} ${category.unit}")
		} else {
			Text("No data to show")
		}
	}
}