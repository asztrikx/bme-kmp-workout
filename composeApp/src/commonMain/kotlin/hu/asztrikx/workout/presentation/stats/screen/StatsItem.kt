package hu.asztrikx.workout.presentation.stats.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.model.Category
import hu.asztrikx.workout.presentation.shared.format
import hu.asztrikx.workout.model.QuantityWithDate

@Composable
fun StatsItem(category: Category, quantityWithDates: List<QuantityWithDate>) {
	Column {
		Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
			Icon(category.icon, null, modifier = Modifier.size(40.dp))
			Spacer(Modifier.width(10.dp))
			Text(category.name, style = MaterialTheme.typography.titleLarge)
		}
		Spacer(Modifier.height(10.dp))
		if (quantityWithDates.size >= 2) {
			Box(Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp)) {
				Chart(category, quantityWithDates)
			}
		} else if (quantityWithDates.size == 1) {
			Text("${quantityWithDates.first().date.format()} ${quantityWithDates.first().count} ${category.unit}")
		} else {
			Text("No data to show")
		}
	}
}