package hu.asztrikx.workout.database.category

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
	fun find(id: Long): Flow<CategoryEntity>
	suspend fun insert(categoryEntity: CategoryEntity)
	fun getAll(): Flow<List<CategoryEntity>>
	suspend fun update(item: CategoryEntity)
	suspend fun delete(item: CategoryEntity)
}