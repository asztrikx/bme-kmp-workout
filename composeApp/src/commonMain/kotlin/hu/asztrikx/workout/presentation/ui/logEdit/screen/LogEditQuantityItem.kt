package hu.asztrikx.workout.presentation.ui.logEdit.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistWalker
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.service.quantity.Quantity

@Composable
fun LogEditQuantityItem(
	quantity: Quantity,
	onCountChange: (Float?) -> Unit,
) {
	var text by remember { mutableStateOf(quantity.count?.toString() ?: "") }

	Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
		Icon(quantity.category.icon.run { Icons.Default.AssistWalker }, null)
		Spacer(Modifier.width(20.dp))

		OutlinedTextField(
			text,
			{
				text = it
				if (it.isBlank()) {
					onCountChange(null)
				} else {
					it.toFloatOrNull()?.let {
						onCountChange(it)
					}
				}
			},
			label = {
				if (quantity.count == null) {
					Text("${quantity.category.name} (${quantity.category.unit})")
				} else {
					Text(quantity.category.unit)
				}
			},
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
		)
	}
}