package hu.asztrikx.workout

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import hu.asztrikx.workout.log.screen.LogScreen
import hu.asztrikx.workout.log.LogViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Composable
@Preview
fun App() {
	MaterialTheme {
		LogScreen()
	}
}

val viewModels = module {
	single { LogViewModel() }
}

fun initKoin(config: KoinAppDeclaration? = null) {
	startKoin {
		config?.invoke(this)
		modules(viewModels)
	}
}