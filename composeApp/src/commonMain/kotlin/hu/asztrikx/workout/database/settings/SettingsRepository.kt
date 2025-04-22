package hu.asztrikx.workout.database.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
	suspend fun insert(item: SettingsEntity)
	fun getAllWithCategories(): Flow<List<SettingsWithCategories>>
	suspend fun update(item: SettingsEntity)
	suspend fun delete(item: SettingsEntity)
}