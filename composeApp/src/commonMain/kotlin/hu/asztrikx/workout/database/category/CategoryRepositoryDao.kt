package hu.asztrikx.workout.database.category

import kotlinx.coroutines.flow.Flow

class CategoryRepositoryDao(private val dao: CategoryDao): CategoryRepository {
	override fun find(id: Long) =
		dao.find(id)

	override suspend fun insert(categoryEntity: CategoryEntity) =
		dao.insert(categoryEntity)

	override fun getAllNotDeleted(): Flow<List<CategoryEntity>> =
		dao.getAllNotDeleted()

	override suspend fun update(item: CategoryEntity) =
		dao.update(item)

	override suspend fun delete(item: CategoryEntity) =
		dao.softDelete(item.id)
}