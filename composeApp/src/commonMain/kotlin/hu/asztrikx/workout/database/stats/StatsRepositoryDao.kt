package hu.asztrikx.workout.database.stats

class StatsRepositoryDao(private val dao: StatsDao): StatsRepository {
	override fun getAllFlattened() =
		dao.getFlattened()
}