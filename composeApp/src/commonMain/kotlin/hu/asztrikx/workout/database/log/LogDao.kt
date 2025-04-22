package hu.asztrikx.workout.database.log

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
	@Insert
	suspend fun insert(item: LogEntity)

	@Query("SELECT * FROM LogEntity")
	fun getAllWithQuantityAndCategory(): Flow<List<LogWithQuantitiesAndCategories>>

	@Update
	suspend fun update(item: LogEntity)

	@Delete
	suspend fun delete(item: LogEntity)

	@Transaction
	@Query("SELECT * FROM LogEntity")
	fun getLogWithQuantities(): Flow<List<LogWithQuantitiesAndCategories>>
}