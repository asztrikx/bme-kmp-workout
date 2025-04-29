package hu.asztrikx.workout.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import hu.asztrikx.workout.database.category.CategoryDao
import hu.asztrikx.workout.database.category.CategoryEntity
import hu.asztrikx.workout.database.log.LogDao
import hu.asztrikx.workout.database.log.LogEntity
import hu.asztrikx.workout.database.quantity.QuantityDao
import hu.asztrikx.workout.database.quantity.QuantityEntity
import hu.asztrikx.workout.database.settings.SettingsDao
import hu.asztrikx.workout.database.settings.SettingsEntity
import hu.asztrikx.workout.database.stats.StatsDao
import kotlinx.coroutines.Dispatchers

@Database(
	entities = [
		CategoryEntity::class,
		LogEntity::class,
		QuantityEntity::class,
		SettingsEntity::class,
	],
	version = 5
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(Converters::class)
abstract class WorkoutDatabase : RoomDatabase() {
	abstract val categoryDao: CategoryDao
	abstract val logDao: LogDao
	abstract val quantityDao: QuantityDao
	abstract val settingsDao: SettingsDao
	abstract val statsDao: StatsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<WorkoutDatabase> {
	override fun initialize(): WorkoutDatabase
}

fun getRoomDatabase(
	builder: RoomDatabase.Builder<WorkoutDatabase>
): WorkoutDatabase {
	return builder
		.addMigrations()
		.fallbackToDestructiveMigrationOnDowngrade(true)
		.fallbackToDestructiveMigration(true)
		.setDriver(BundledSQLiteDriver())
		.setQueryCoroutineContext(Dispatchers.IO)
		.build()
}