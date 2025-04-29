package hu.asztrikx.workout.service.settings

import hu.asztrikx.workout.database.settings.SettingsRepository
import hu.asztrikx.workout.presentation.ui.shared.currentDate
import hu.asztrikx.workout.service.category.asModel
import jdk.internal.vm.vector.VectorSupport.insert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsService(private val repository: SettingsRepository) {
	suspend fun insert(item: Settings) =
		repository.insert(item.asEntity())

	fun getAllWithCategories(): Flow<List<Settings>> {
		// We could do this with cross join (Settings x Category) but it wouldn't be transactional
		return repository.getAllWithCategories().map { list ->
			list.map { it.asModel() }
		}
	}

	suspend fun update(item: Settings) =
		repository.update(item.asEntity())

	suspend fun delete(item: Settings) =
		repository.delete(item.asEntity())

	// Populates database
	suspend fun initialize() {
		if (getAllWithCategories().first().isEmpty()) {
			insert(
				Settings(
					currentDate(),
					listOf(),
				)
			)
		}
	}
}