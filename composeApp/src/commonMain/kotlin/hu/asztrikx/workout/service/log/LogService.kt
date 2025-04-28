package hu.asztrikx.workout.service.log

import hu.asztrikx.workout.database.PLACEHOLDER
import hu.asztrikx.workout.database.log.LogRepository
import hu.asztrikx.workout.service.quantity.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LogService(private val repository: LogRepository) {
	suspend fun insert(item: Log) = item.run {
		repository.insertWithQuantities(
			asEntity(),
			item.quantities.map { it.asEntity(PLACEHOLDER) }
		)
	}

	fun getAllWithQuantityAndCategory(): Flow<List<Log>> =
		repository.getAllWithQuantityAndCategory().map { list ->
			list.map { it.asModel() }
		}

	suspend fun update(item: Log) = item.run {
		repository.updateWithQuantities(
			asEntity(),
			item.quantities.map { it.asEntity(id) }
		)
	}

	suspend fun delete(item: Log) = item.run {
		repository.delete(asEntity())
	}
}