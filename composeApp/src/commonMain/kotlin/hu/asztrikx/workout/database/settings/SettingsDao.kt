package hu.asztrikx.workout.database.settings

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
	@Insert
	suspend fun insert(item: SettingsEntity)

	@Transaction
	@Query("SELECT * FROM SettingsEntity")
	fun getAll(): Flow<List<SettingsWithCategories>>

	@Update
	suspend fun update(item: SettingsEntity)

	@Delete
	suspend fun delete(item: SettingsEntity)
}