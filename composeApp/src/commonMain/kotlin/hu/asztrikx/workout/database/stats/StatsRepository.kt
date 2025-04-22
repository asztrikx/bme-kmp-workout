package hu.asztrikx.workout.database.stats

import kotlinx.coroutines.flow.Flow

interface StatsRepository {
	fun getAllFlattened(): Flow<List<Stat>>
}