package hu.asztrikx.workout.database.settings

import kotlinx.coroutines.flow.map

class SettingsRepositoryDao(private val dao: SettingsDao): SettingsRepository {
	override suspend fun insert(item: SettingsEntity) =
		dao.insert(item)

	override fun getAllWithCategories() =
		dao.getAll().map { list ->
			list.map {
				it.copy(categories = it.categories.filter { !it.isDeleted })
			}
		}

	override suspend fun update(item: SettingsEntity) =
		dao.update(item)

	override suspend fun delete(item: SettingsEntity) =
		dao.delete(item)
}