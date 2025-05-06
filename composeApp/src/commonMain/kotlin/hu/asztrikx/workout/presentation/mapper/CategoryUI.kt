package hu.asztrikx.workout.presentation.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsBike
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsCricket
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.SportsFootball
import androidx.compose.material.icons.filled.SportsGolf
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material.icons.filled.SportsKabaddi
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.SportsMma
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material.icons.filled.Timer
import androidx.compose.ui.graphics.vector.ImageVector
import hu.asztrikx.workout.service.category.Category

data class CategoryUI(
	val id: Long,
	val icon: ImageVector,
	val iconName: String,
	val name: String,
	val unit: String,
)

fun CategoryUI.asModel() = Category(
	id,
	iconName,
	name,
	unit,
)

fun Category.asUI() = CategoryUI(
	id,
	iconsMap[iconName]!!,
	iconName,
	name,
	unit,
)

val iconsMap = listOf(
	Icons.Default.FitnessCenter,
	Icons.Filled.SportsGymnastics,
	Icons.AutoMirrored.Filled.DirectionsRun,
	Icons.Filled.SportsMartialArts,
	Icons.Filled.SelfImprovement,
	Icons.Filled.SportsScore,
	Icons.Filled.SportsKabaddi,
	Icons.Filled.SportsHandball,
	Icons.Filled.SportsMma,
	Icons.Filled.SportsSoccer,
	Icons.Filled.SportsBasketball,
	Icons.Filled.SportsTennis,
	Icons.Filled.SportsCricket,
	Icons.Filled.SportsGolf,
	Icons.Filled.SportsFootball,
	Icons.Filled.SportsVolleyball,
	Icons.Default.Timer,
	Icons.AutoMirrored.Filled.DirectionsBike,
	Icons.Default.Hiking,
	Icons.Default.Pool,
).associateBy { it.name }