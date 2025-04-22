package hu.asztrikx.workout.database.log

import kotlinx.coroutines.flow.Flow

interface LogRepository {
	suspend fun insert(item: LogEntity)
	fun getAllWithQuantityAndCategory(): Flow<List<LogWithQuantitiesAndCategories>>
	suspend fun update(item: LogEntity)
	suspend fun delete(item: LogEntity)
}