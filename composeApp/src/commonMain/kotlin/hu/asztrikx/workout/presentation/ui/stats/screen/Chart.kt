package hu.asztrikx.workout.presentation.ui.stats.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import hu.asztrikx.workout.presentation.mapper.CategoryUI
import hu.asztrikx.workout.presentation.ui.shared.format
import hu.asztrikx.workout.service.stats.QuantityWithDate
import io.github.dautovicharis.charts.LineChart
import io.github.dautovicharis.charts.model.toChartDataSet
import io.github.dautovicharis.charts.style.ChartViewDefaults
import io.github.dautovicharis.charts.style.LineChartDefaults

@Composable
fun Chart(category: CategoryUI, quantityWithDates: List<QuantityWithDate>) {
	val counts = quantityWithDates.map { it.count }

	val dataSet = counts.toChartDataSet(
		title = "",
		labels = quantityWithDates.map { "${it.date.format()} ${it.count} ${category.unit}" },
	)
	val style = LineChartDefaults.style(
		chartViewStyle = ChartViewDefaults.style(
			outerPadding = 0.dp,
			cornerRadius = 10.dp,
			shadow = 0.dp,
			backgroundColor = MaterialTheme.colorScheme.primaryContainer,
		)
	)
	LineChart(dataSet, style)
}