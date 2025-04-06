package hu.asztrikx.workout.logEdit.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.quantity.Quantity

@Composable
fun LogEditQuantityItem(
	quantity: Quantity,
	quantityCount: Int?,
	onQuantityChane: (Int?) -> Unit
) {
	Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
		Icon(quantity.category.icon, null)
		Spacer(Modifier.width(20.dp))
		OutlinedTextField(
			quantityCount?.toString() ?: "",
			{ onQuantityChane(it.toIntOrNull()) },
			label = {
				if (quantityCount == null) {
					Text("""Quantity of unit "${quantity.category.unit}" """)
				} else {
					Text(quantity.category.unit)
				}
			},
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
			modifier = Modifier.minimumInteractiveComponentSize(),
		)
	}
}