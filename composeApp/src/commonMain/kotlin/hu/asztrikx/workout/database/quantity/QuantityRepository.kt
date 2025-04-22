package hu.asztrikx.workout.database.quantity

import kotlinx.coroutines.flow.Flow

interface QuantityRepository {
	fun findWithCategory(id: Long): Flow<QuantityWithCategory>
	suspend fun insert(item: QuantityEntity)
	fun getAll(): Flow<List<QuantityEntity>>
	suspend fun update(item: QuantityEntity)
	suspend fun delete(item: QuantityEntity)
}