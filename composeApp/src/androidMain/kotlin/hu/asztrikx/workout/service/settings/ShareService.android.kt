package hu.asztrikx.workout.service.settings

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

actual class ShareService(private val context: Context) {
	actual suspend fun exportCsv(text: String) {
		val fileName = "statistics.csv"
		val file = File(context.cacheDir, fileName)
		file.writeText(text)

		val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)

		var shareIntent = Intent(Intent.ACTION_SEND).apply {
			type = "text/csv"
			putExtra(Intent.EXTRA_STREAM, uri)
			addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
		}

		shareIntent = Intent.createChooser(shareIntent, "Share CSV")
		shareIntent = shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

		context.startActivity(shareIntent)
	}
}