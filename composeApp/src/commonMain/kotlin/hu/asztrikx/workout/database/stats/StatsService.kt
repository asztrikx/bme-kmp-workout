package hu.asztrikx.workout.database.stats

import hu.asztrikx.workout.database.GENERATE
import hu.asztrikx.workout.model.Category
import hu.asztrikx.workout.model.QuantityWithDate
import hu.asztrikx.workout.model.Stats
import kotlinx.coroutines.flow.map

class StatsService(private val repository: StatsRepository) {
	fun getAll() =
		repository.getAllFlattened().map { list ->
			list.groupBy { it.categoryName to it.categoryUnit }
				.map { (category, items) ->
					val (categoryName, categoryUnit) = category
					Stats(
						Category(
							GENERATE,
							name = categoryName,
							icon = "TODO",
							unit = categoryUnit
						),
						stats = items.map {
							QuantityWithDate(
								it.count,
								it.logDate
							)
						}
					)
				}
		}
}