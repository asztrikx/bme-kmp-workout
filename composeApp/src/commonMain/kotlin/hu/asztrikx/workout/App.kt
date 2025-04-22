package hu.asztrikx.workout

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import hu.asztrikx.workout.database.WorkoutDatabase
import hu.asztrikx.workout.database.category.CategoryRepository
import hu.asztrikx.workout.database.category.CategoryRepositoryDao
import hu.asztrikx.workout.database.category.CategoryService
import hu.asztrikx.workout.database.log.LogRepository
import hu.asztrikx.workout.database.log.LogRepositoryDao
import hu.asztrikx.workout.database.log.LogService
import hu.asztrikx.workout.database.quantity.QuantityRepository
import hu.asztrikx.workout.database.quantity.QuantityRepositoryDao
import hu.asztrikx.workout.database.settings.SettingsRepository
import hu.asztrikx.workout.database.settings.SettingsRepositoryDao
import hu.asztrikx.workout.database.settings.SettingsService
import hu.asztrikx.workout.database.stats.StatsRepository
import hu.asztrikx.workout.database.stats.StatsRepositoryDao
import hu.asztrikx.workout.database.stats.StatsService
import hu.asztrikx.workout.presentation.log.LogViewModel
import hu.asztrikx.workout.presentation.logEdit.LogEditViewModel
import hu.asztrikx.workout.presentation.navigation.NavGraph
import hu.asztrikx.workout.presentation.settings.SettingsViewModel
import hu.asztrikx.workout.presentation.settings.categoryEdit.CategoryEditViewModel
import hu.asztrikx.workout.presentation.stats.StatsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.core.module.Module
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
expect fun platformModule(): Module

val repositories = module {
	single<CategoryRepository> { CategoryRepositoryDao(get<WorkoutDatabase>().categoryDao) }
	single<LogRepository> { LogRepositoryDao(get<WorkoutDatabase>().logDao) }
	single<QuantityRepository> { QuantityRepositoryDao(get<WorkoutDatabase>().quantityDao) }
	single<SettingsRepository> { SettingsRepositoryDao(get<WorkoutDatabase>().settingsDao) }
	single<StatsRepository> { StatsRepositoryDao(get<WorkoutDatabase>().statsDao) }
}

val services = module {
	single { CategoryService(get<CategoryRepository>()) }
	single { LogService(get<LogRepository>()) }
	//single { QuantityService(get<QuantityRepository>()) }
	single { SettingsService(get<SettingsRepository>()) }
	single { StatsService(get<StatsRepository>()) }
}

val viewModels = module {
	single { CategoryEditViewModel(get<CategoryService>()) }
	single { LogViewModel(get<LogService>()) }
	single { LogEditViewModel(get<LogService>()) }
	single { SettingsViewModel(get<SettingsService>()) }
	single { StatsViewModel(get<StatsService>()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
	startKoin {
		config?.invoke(this)
		modules(platformModule(), repositories, services, viewModels)
	}
}