package hu.asztrikx.workout.database.settings

class SettingsRepositoryDao(private val dao: SettingsDao): SettingsRepository {
	override suspend fun insert(item: SettingsEntity) =
		dao.insert(item)

	override fun getAllWithCategories() =
		dao.getAll()

	override suspend fun update(item: SettingsEntity) =
		dao.update(item)

	override suspend fun delete(item: SettingsEntity) =
		dao.delete(item)
}