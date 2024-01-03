package aleshka.developement.exchanger.feature_top.presentation.screens

import aleshka.developement.exchanger.R
import aleshka.developement.exchanger.feature_top.domain.view_models.TopViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@Composable
@Destination
fun TopScreen() {

    val viewModel = koinViewModel<TopViewModel>()
    val state = viewModel.state.collectAsState().value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.base,
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 40.sp,
                fontFamily = FontFamily(Font(R.font.pacifico)),
                fontWeight = FontWeight(600),
                color = MaterialTheme.colorScheme.onBackground,
            )
        )

        if (!state.isLoading) {

            val xAxisData = AxisData.Builder()
                .axisStepSize(30.dp)
                .steps(state.barData.size - 1)
                .bottomPadding(40.dp)
                .axisLabelAngle(20f)
                .labelData { i ->
                    state.pointNames[i]
                }
                .build()

            val yAxisData = AxisData.Builder()
                .steps(state.barData.size)
                .labelAndAxisLinePadding(20.dp)
                .axisOffset(0.dp)
                .labelData {
                    i ->
                    (i*(state.theHighest / (state.amountColumns + 1))).toString()
                }
                .build()

            val barChartData = BarChartData(
                chartData = state.barData,
                xAxisData = xAxisData,
                yAxisData = yAxisData,
            )

            BarChart(
                modifier = Modifier
                    .fillMaxWidth(),
                barChartData = barChartData
            )
        }
    }
}
