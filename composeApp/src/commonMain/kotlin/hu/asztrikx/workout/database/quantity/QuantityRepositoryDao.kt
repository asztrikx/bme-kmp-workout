package hu.asztrikx.workout.database.quantity

import kotlinx.coroutines.flow.Flow

class QuantityRepositoryDao(private val dao: QuantityDao): QuantityRepository {
	override fun findWithCategory(id: Long) =
		dao.findWithCategory(id)

	override suspend fun insert(item: QuantityEntity) =
		dao.insert(item)

	override fun getAll(): Flow<List<QuantityEntity>> =
		dao.getAll()

	override suspend fun update(item: QuantityEntity) =
		dao.update(item)

	override suspend fun delete(item: QuantityEntity) =
		dao.delete(item)
}