package hu.asztrikx.workout.presentation.logEdit.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.quantity.Quantity

@Composable
fun LogEditQuantityItem(
	quantity: Quantity,
	onCountChange: (Float?) -> Unit,
) {
	Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
		Icon(quantity.category.icon, null)
		Spacer(Modifier.width(20.dp))
		OutlinedTextField(
			quantity.count?.toString() ?: "",
			{ onCountChange(it.toFloatOrNull()) },
			label = {
				if (quantity.count == null) {
					Text("""Quantity of unit "${quantity.category.unit}" """)
				} else {
					Text(quantity.category.unit)
				}
			},
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
		)
	}
}