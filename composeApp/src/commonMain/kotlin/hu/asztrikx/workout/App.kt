package hu.asztrikx.workout

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import hu.asztrikx.workout.log.LogViewModel
import hu.asztrikx.workout.logEdit.LogEditViewModel
import hu.asztrikx.workout.navigation.NavGraph
import hu.asztrikx.workout.settings.SettingsViewModel
import hu.asztrikx.workout.settings.categoryEdit.CategoryEditViewModel
import hu.asztrikx.workout.stats.StatsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Composable
@Preview
fun App() {
	val navHostController = rememberNavController()

	MaterialTheme {
		NavGraph(navHostController)
	}
}

val viewModels = module {
	single { LogViewModel() }
	single { LogEditViewModel() }
	single { SettingsViewModel() }
	single { StatsViewModel() }
	single { CategoryEditViewModel() }
}

fun initKoin(config: KoinAppDeclaration? = null) {
	startKoin {
		config?.invoke(this)
		modules(viewModels)
	}
}