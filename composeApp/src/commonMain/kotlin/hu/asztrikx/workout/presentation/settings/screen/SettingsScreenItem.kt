package hu.asztrikx.workout.presentation.settings.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.model.Category
import hu.asztrikx.workout.presentation.settings.categoryEdit.CategoryEditDialog

@Composable
fun SettingsScreenItem(category: Category) {
	var categoryEditShow by remember { mutableStateOf(false) }

	Card({ categoryEditShow = !categoryEditShow }) {
		Row(Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
			Icon(category.icon, null)
			Text(category.name)
			Text(category.unit)
		}
	}
	Spacer(Modifier.height(20.dp))

	if (categoryEditShow) {
		CategoryEditDialog(
			"Edit category",
			{ categoryEditShow = !categoryEditShow },
			{ categoryEditShow = !categoryEditShow },
			category.id,
		)
	}
}