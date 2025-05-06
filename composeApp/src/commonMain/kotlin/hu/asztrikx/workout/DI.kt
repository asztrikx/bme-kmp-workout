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

val daos = module {
	includes(platformModule())
	single { get<WorkoutDatabase>().categoryDao }
	single { get<WorkoutDatabase>().logDao }
	single { get<WorkoutDatabase>().quantityDao }
	single { get<WorkoutDatabase>().settingsDao }
	single { get<WorkoutDatabase>().statsDao }
}

val repositories = module {
	includes(daos)
	single<CategoryRepository> { CategoryRepositoryDao(get()) }
	single<LogRepository> { LogRepositoryDao(get()) }
	single<QuantityRepository> { QuantityRepositoryDao(get()) }
	single<SettingsRepository> { SettingsRepositoryDao(get()) }
	single<StatsRepository> { StatsRepositoryDao(get()) }
}

val services = module {
	includes(repositories, platformModule())
	single { CategoryService(get()) }
	single { LogService(get()) }
	single { SettingsService(get(), get(), get()) }
	single { StatsService(get()) }
}

val viewModels = module {
	includes(services)
	viewModel { CategoryEditViewModel(get()) }
	viewModel { LogViewModel(get()) }
	viewModel { LogEditViewModel(get(), get()) }
	viewModel { SettingsViewModel(get()) }
	viewModel { StatsViewModel(get(), get()) }
	viewModel { SettingsScreenItemViewModel(get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
	startKoin {
		config?.invoke(this)
		modules(
			services, // Database populate
			viewModels
		)
	}
}