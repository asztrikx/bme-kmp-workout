package hu.asztrikx.workout.service.stats

import hu.asztrikx.workout.database.PLACEHOLDER
import hu.asztrikx.workout.database.stats.StatsRepository
import hu.asztrikx.workout.service.category.Category
import kotlinx.coroutines.flow.map

class StatsService(private val repository: StatsRepository) {
	fun getAll() =
		repository.getAllFlattened().map { list ->
			list.groupBy { it.categoryName to it.categoryUnit to it.categoryIconName }
				.map { (category, items) ->
					val (categoryNameAndUnit, categoryIconName) = category
					val (categoryName, categoryUnit) = categoryNameAndUnit
					Stats(
						Category(
							PLACEHOLDER,
							name = categoryName,
							iconName = categoryIconName,
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