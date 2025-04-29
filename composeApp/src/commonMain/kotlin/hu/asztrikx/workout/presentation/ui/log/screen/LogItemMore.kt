package hu.asztrikx.workout.presentation.ui.log.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistWalker
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.service.log.Log

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LogItemMore(log: Log, isExpanded: Boolean) {
	AnimatedVisibility(
		isExpanded,
		enter = slideInVertically() + expandVertically() + fadeIn(),
		exit = slideOutVertically() + shrinkVertically() + fadeOut()
	) {
		Card(
			Modifier.fillMaxWidth().padding(40.dp, 5.dp),
			shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)
		) {
			FlowRow(
				Modifier.fillMaxWidth(),
				maxItemsInEachRow = 2,
				horizontalArrangement = Arrangement.SpaceAround,
			) {
				log.quantities.forEach { quantity ->
					if (quantity.count == null) return@forEach
					Row(Modifier.padding(10.dp)) {
						Icon(quantity.category.icon.run { Icons.Default.AssistWalker }, null)
						Spacer(Modifier.width(10.dp))
						Text("%.2f %s".format(quantity.count, quantity.category.unit))
					}
				}
			}
		}
	}
}