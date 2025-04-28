package hu.asztrikx.workout.service.settings

import hu.asztrikx.workout.database.category.asModel
import hu.asztrikx.workout.database.settings.SettingsRepository
import hu.asztrikx.workout.database.settings.asEntity
import hu.asztrikx.workout.presentation.shared.currentDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsService(private val repository: SettingsRepository) {
	suspend fun insert(item: Settings) =
		repository.insert(item.asEntity())

	fun getAllWithCategories(): Flow<List<Settings>> {
		// We could do this with cross join (Settings x Category) but it wouldn't be transactional
		return repository.getAllWithCategories().map { list ->
			list.map { it.run {
				Settings(
					startDate = settings.startDate,
					categories = categories.map { it.asModel() },
				)
			}}
		}
	}

	suspend fun update(item: Settings) =
		repository.update(item.asEntity())

	suspend fun delete(item: Settings) =
		repository.delete(item.asEntity())

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