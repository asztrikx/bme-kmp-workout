package hu.asztrikx.workout.service.settings

expect class ShareService {
	suspend fun exportCsv(text: String)
}