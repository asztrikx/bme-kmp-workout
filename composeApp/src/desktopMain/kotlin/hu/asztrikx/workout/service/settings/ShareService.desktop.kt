package hu.asztrikx.workout.service.settings

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.dialogs.openFileSaver
import io.github.vinceglb.filekit.writeString

actual class ShareService {
	actual suspend fun exportCsv(text: String) {
		val file = FileKit.openFileSaver(suggestedName = "statistics", extension = "csv")
		file?.writeString(text)
	}
}