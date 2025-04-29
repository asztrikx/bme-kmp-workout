package hu.asztrikx.workout

import hu.asztrikx.workout.database.WorkoutDatabase
import hu.asztrikx.workout.database.category.CategoryRepository
import hu.asztrikx.workout.database.category.CategoryRepositoryDao
import hu.asztrikx.workout.service.category.CategoryService
import hu.asztrikx.workout.database.log.LogRepository
import hu.asztrikx.workout.database.log.LogRepositoryDao
import hu.asztrikx.workout.service.log.LogService
import hu.asztrikx.workout.database.quantity.QuantityRepository
import hu.asztrikx.workout.database.quantity.QuantityRepositoryDao
import hu.asztrikx.workout.database.settings.SettingsRepository
import hu.asztrikx.workout.database.settings.SettingsRepositoryDao
import hu.asztrikx.workout.service.settings.SettingsService
import hu.asztrikx.workout.database.stats.StatsRepository
import hu.asztrikx.workout.database.stats.StatsRepositoryDao
import hu.asztrikx.workout.service.stats.StatsService
import hu.asztrikx.workout.presentation.ui.log.LogViewModel
import hu.asztrikx.workout.presentation.ui.logEdit.LogEditViewModel
import hu.asztrikx.workout.presentation.ui.settings.SettingsViewModel
import hu.asztrikx.workout.presentation.ui.categoryEdit.CategoryEditViewModel
import hu.asztrikx.workout.presentation.ui.settings.screen.SettingsScreenItemViewModel
import hu.asztrikx.workout.presentation.ui.stats.StatsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

val repositories = module {
	includes(platformModule())
	single<CategoryRepository> { CategoryRepositoryDao(get<WorkoutDatabase>().categoryDao) }
	single<LogRepository> { LogRepositoryDao(get<WorkoutDatabase>().logDao) }
	single<QuantityRepository> { QuantityRepositoryDao(get<WorkoutDatabase>().quantityDao) }
	single<SettingsRepository> { SettingsRepositoryDao(get<WorkoutDatabase>().settingsDao) }
	single<StatsRepository> { StatsRepositoryDao(get<WorkoutDatabase>().statsDao) }
}

val services = module {
	includes(repositories)
	single { CategoryService(get()) }
	single { LogService(get()) }
	//single { QuantityService(get<QuantityRepository>()) }
	single { SettingsService(get()) }
	single { StatsService(get()) }
}

val viewModels = module {
	includes(services)
	viewModel { CategoryEditViewModel(get()) }
	viewModel { LogViewModel(get()) }
	viewModel { LogEditViewModel(get(), get()) }
	viewModel { SettingsViewModel(get()) }
	viewModel { StatsViewModel(get()) }
	viewModel { SettingsScreenItemViewModel(get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
	startKoin {
		config?.invoke(this)
		modules(viewModels)
	}
}