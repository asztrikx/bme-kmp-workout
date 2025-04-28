package hu.asztrikx.workout.database.log

import hu.asztrikx.workout.database.quantity.QuantityEntity

class LogRepositoryDao(private val dao: LogDao): LogRepository {
	override suspend fun insert(item: LogEntity) =
		dao.insert(item)

	override fun getAllWithQuantityAndCategory() =
		dao.getAllWithQuantityAndCategory()

	override suspend fun update(item: LogEntity) =
		dao.update(item)

	override suspend fun delete(item: LogEntity) =
		dao.delete(item)

	override suspend fun insertWithQuantities(log: LogEntity, quantities: List<QuantityEntity>) =
		dao.insertWithQuantities(log, quantities)

	override suspend fun updateWithQuantities(log: LogEntity, quantities: List<QuantityEntity>) =
		dao.updateWithQuantities(log, quantities)
}