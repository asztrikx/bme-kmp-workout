package hu.asztrikx.workout.database.log

import hu.asztrikx.workout.database.quantity.QuantityEntity
import kotlinx.coroutines.flow.Flow

interface LogRepository {
	suspend fun insert(item: LogEntity): Long
	fun getAllWithQuantityAndCategory(): Flow<List<LogWithQuantitiesAndCategories>>
	suspend fun update(item: LogEntity)
	suspend fun delete(item: LogEntity)
	suspend fun insertWithQuantities(log: LogEntity, quantities: List<QuantityEntity>)
	suspend fun updateWithQuantities(log: LogEntity, quantities: List<QuantityEntity>)
}