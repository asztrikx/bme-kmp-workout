package hu.asztrikx.workout.database.log

import kotlinx.coroutines.flow.Flow

class LogRepositoryDao(private val dao: LogDao): LogRepository {
	override suspend fun insert(item: LogEntity) =
		dao.insert(item)

	override fun getAllWithQuantityAndCategory() =
		dao.getAllWithQuantityAndCategory()

	override suspend fun update(item: LogEntity) =
		dao.update(item)

	override suspend fun delete(item: LogEntity) =
		dao.delete(item)
}