package hu.asztrikx.workout.database.quantity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuantityDao {
	@Transaction
	@Query("SELECT * FROM QuantityEntity WHERE id = :id")
	fun findWithCategory(id: Long): Flow<QuantityWithCategory>

	@Insert
	suspend fun insert(item: QuantityEntity)

	@Query("SELECT * FROM QuantityEntity")
	fun getAll(): Flow<List<QuantityEntity>>

	@Update
	suspend fun update(item: QuantityEntity)

	@Delete
	suspend fun delete(item: QuantityEntity)
}