package hu.asztrikx.workout.database.stats

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsDao {
	@Query("""
        SELECT 
            c.name AS categoryName,
            c.unit AS categoryUnit,
			c.iconName as categoryIconName,
            q.count AS count,
            l.date AS logDate
        FROM QuantityEntity q
        INNER JOIN CategoryEntity c ON q.categoryId = c.id
        INNER JOIN LogEntity l ON q.logId = l.id
        ORDER BY c.name, l.date
    """)
	fun getFlattened(): Flow<List<StatEntity>>
}