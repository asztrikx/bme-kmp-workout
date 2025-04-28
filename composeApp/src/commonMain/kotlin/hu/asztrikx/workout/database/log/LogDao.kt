package hu.asztrikx.workout.database.log

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import hu.asztrikx.workout.database.quantity.QuantityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
	@Insert
	suspend fun insert(item: LogEntity): Long
		// Can't make this private :/

	@Transaction
	@Query("SELECT * FROM LogEntity")
	fun getAllWithQuantityAndCategory(): Flow<List<LogWithQuantitiesAndCategories>>

	@Update
	suspend fun update(item: LogEntity)

	@Delete
	suspend fun delete(item: LogEntity)

	@Transaction
	@Query("SELECT * FROM LogEntity")
	fun getLogWithQuantities(): Flow<List<LogWithQuantitiesAndCategories>>

	@Insert
	suspend fun insertQuantities(quantities: List<QuantityEntity>)

	@Transaction
	suspend fun insertWithQuantities(
		log: LogEntity,
		quantities: List<QuantityEntity>
	) {
		val logId = insert(log)

		val quantitiesWithLogId = quantities.map { it.copy(logId = logId) }
		insertQuantities(quantitiesWithLogId)
	}

	@Update
	suspend fun updateQuantities(quantities: List<QuantityEntity>)

	@Transaction
	suspend fun updateWithQuantities(
		log: LogEntity,
		quantities: List<QuantityEntity>
	) {
		update(log)
		updateQuantities(quantities)
	}
}