package hu.asztrikx.workout.service.category

import hu.asztrikx.workout.database.category.CategoryRepository
import kotlinx.coroutines.flow.map

class CategoryService(private val repository: CategoryRepository) {
	suspend fun insert(item: Category) =
		repository.insert(item.asEntity(false))

	fun getAllNotDeleted() =
		repository.getAllNotDeleted().map { list ->
			list.map { it.asModel() }
		}

	suspend fun update(item: Category) =
		repository.update(item.asEntity(false))

	suspend fun delete(item: Category) =
		repository.delete(item.asEntity(true))

	fun find(id: Long) =
		repository.find(id).map { it.asModel() }
}