package hu.asztrikx.workout.service.log

import hu.asztrikx.workout.database.PLACEHOLDER
import hu.asztrikx.workout.database.log.LogEntity
import hu.asztrikx.workout.database.log.LogRepository
import hu.asztrikx.workout.database.quantity.QuantityEntity
import hu.asztrikx.workout.service.quantity.asModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LogService(private val repository: LogRepository) {
	suspend fun insert(item: Log) = item.run {
		repository.insertWithQuantities(
			LogEntity(
				id,
				date,
			),
			item.quantities.map {
				QuantityEntity(
					it.id,
					it.category.id,
					PLACEHOLDER,
					it.count,
				)
			}
		)
	}

	fun getAllWithQuantityAndCategory(): Flow<List<Log>> =
		repository.getAllWithQuantityAndCategory().map { list ->
			list.map { it.run {
				Log(
					logEntity.id,
					logEntity.date,
					quantities = quantityWithCategories.map { it.asModel() },
				)
			}}
		}

	suspend fun update(item: Log) = item.run {
		repository.updateWithQuantities(
			LogEntity(
				id,
				date,
			),
			item.quantities.map { QuantityEntity(
				it.id,
				it.category.id,
				id,
				it.count,
			) }
		)
	}

	suspend fun delete(item: Log) = item.run {
		repository.delete(
			LogEntity(
			id,
			date,
		)
		)
	}
}