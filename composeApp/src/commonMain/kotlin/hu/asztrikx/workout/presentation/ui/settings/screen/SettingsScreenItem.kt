package hu.asztrikx.workout.presentation.ui.settings.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssistWalker
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.mapper.CategoryUI
import hu.asztrikx.workout.presentation.ui.categoryEdit.CategoryEditDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreenItem(category: CategoryUI) {
	val viewModel: SettingsScreenItemViewModel = koinViewModel()
	var categoryEditShow by remember { mutableStateOf(false) }

	Card({ categoryEditShow = !categoryEditShow }) {
		Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
			Row(Modifier.weight(1f).padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween) {
				Icon(category.icon, null)
				Text(category.name)
				Text(category.unit)
			}
			IconButton({ viewModel.delete(category) }) {
				Icon(Icons.Default.Delete, null)
			}
		}
	}
	Spacer(Modifier.height(20.dp))

	if (categoryEditShow) {
		CategoryEditDialog(
			"Edit category",
			"Edit",
			{ categoryEditShow = !categoryEditShow },
			{ categoryEditShow = !categoryEditShow },
			category.id,
		)
	}
}