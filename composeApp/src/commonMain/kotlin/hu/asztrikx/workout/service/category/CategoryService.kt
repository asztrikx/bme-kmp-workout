package hu.asztrikx.workout.service.category

import hu.asztrikx.workout.database.category.CategoryRepository
import hu.asztrikx.workout.database.category.asEntity
import hu.asztrikx.workout.database.category.asModel
import kotlinx.coroutines.flow.map

class CategoryService(private val repository: CategoryRepository) {
	suspend fun insert(item: Category) =
		repository.insert(item.asEntity())

	fun getAll() =
		repository.getAll().map { list ->
			list.map { it.asModel() }
		}

	suspend fun update(item: Category) =
		repository.update(item.asEntity())

	suspend fun delete(item: Category) =
		repository.delete(item.asEntity())

	fun find(id: Long) =
		repository.find(id).map { it.asModel() }
}