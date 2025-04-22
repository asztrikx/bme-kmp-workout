package hu.asztrikx.workout.database.category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
	@Query("SELECT * FROM CategoryEntity WHERE id = :id")
	fun find(id: Long): Flow<CategoryEntity>

	@Insert
	suspend fun insert(categoryEntity: CategoryEntity)

	@Query("SELECT * FROM CategoryEntity")
	fun getAll(): Flow<List<CategoryEntity>>

	@Update
	suspend fun update(item: CategoryEntity)

	@Delete
	suspend fun delete(item: CategoryEntity)
}