package hu.asztrikx.workout.presentation.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.FitnessCenter
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

val iconsMap = mapOf(
	"FitnessCenter" to Icons.Default.FitnessCenter,
	"Filled_SportsGymnastics" to Icons.Filled.SportsGymnastics,
	"AutoMirrored_DirectionsRun" to Icons.AutoMirrored.Filled.DirectionsRun,
	"Filled_SportsMartialArts" to Icons.Filled.SportsMartialArts,
	"Filled_SelfImprovement" to Icons.Filled.SelfImprovement,
	"Filled_SportsScore" to Icons.Filled.SportsScore,
	"Filled_SportsKabaddi" to Icons.Filled.SportsKabaddi,
	"Filled_SportsHandball" to Icons.Filled.SportsHandball,
	"Filled_SportsMma" to Icons.Filled.SportsMma,
	"Filled_SportsEsports" to Icons.Filled.SportsEsports,
	"Filled_SportsSoccer" to Icons.Filled.SportsSoccer,
	"Filled_SportsBasketball" to Icons.Filled.SportsBasketball,
	"Filled_SportsTennis" to Icons.Filled.SportsTennis,
	"Filled_SportsCricket" to Icons.Filled.SportsCricket,
	"Filled_SportsGolf" to Icons.Filled.SportsGolf,
	"Filled_SportsFootball" to Icons.Filled.SportsFootball,
	"Filled_SportsVolleyball" to Icons.Filled.SportsVolleyball,
	"Filled_AccessibilityNew" to Icons.Filled.AccessibilityNew,
)
