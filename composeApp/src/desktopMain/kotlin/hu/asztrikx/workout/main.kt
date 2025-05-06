package hu.asztrikx.workout

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import workout.composeapp.generated.resources.Res
import workout.composeapp.generated.resources.play_store_512

fun main() = application {
	initKoin()
	Window(
		onCloseRequest = ::exitApplication,
		title = "Workout",
		icon = painterResource(Res.drawable.play_store_512),
	) {
		App()
	}
}