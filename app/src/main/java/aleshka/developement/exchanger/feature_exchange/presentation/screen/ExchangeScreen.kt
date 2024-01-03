package aleshka.developement.exchanger.feature_exchange.presentation.screen

import aleshka.developement.exchanger.R
import aleshka.developement.exchanger.feature_exchange.domain.events.ExchangeEvent
import aleshka.developement.exchanger.feature_exchange.domain.view_models.ExchangeViewModel
import aleshka.developement.exchanger.feature_exchange.presentation.components.DropDown
import aleshka.developement.exchanger.feature_exchange.presentation.components.OutlinedText
import aleshka.developement.exchanger.shared.presentation.modifiers.shimmer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@Composable
@Destination(start = true)
fun ExchangeScreen() {

    val viewModel = getViewModel<ExchangeViewModel>()
    val state = viewModel.state.collectAsState().value

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(scrollState)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                modifier = Modifier
                    .weight(.4f),
                painter = painterResource(id = R.drawable.logo),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "logo icon"
            )

            Text(
                modifier = Modifier
                    .weight(.6f),
                text = stringResource(R.string.app_name),
                style = TextStyle(
                    fontSize = 40.sp,
                    lineHeight = 40.sp,
                    fontFamily = FontFamily(Font(R.font.pacifico)),
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.advice_exchange),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pacifico)),
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.onBackground,
                letterSpacing = 0.26.sp,
            )
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedText(
            label = stringResource(R.string.input_exchange),
            isNumber = true,
            onTextChanged = {
                viewModel.onEvent(ExchangeEvent.OnChangedAmount(it))
            },
            onCompleted = {
                viewModel.onEvent(ExchangeEvent.OnExchangeCurrencies)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DropDown(
                modifier = Modifier
                    .fillMaxWidth(.45f),
                options = state.currencies,
                previousData = state.fromCurrency,
                label = "From currency",
                onTextChanged = {
                    viewModel.onEvent(ExchangeEvent.OnChangedFromCurrency(it))
                }
            )

            Icon(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(.1f),
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "exchange arrow",
                tint = MaterialTheme.colorScheme.onBackground
            )

            DropDown(
                options = state.currencies,
                previousData = state.toCurrency,
                label = "To currency",
                onTextChanged = {
                    viewModel.onEvent(ExchangeEvent.OnChangedToCurrency(it))
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (state.isSearching) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .shimmer()
            )

        } else {

            if (state.result != -1f) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "${state.result} ${state.toCurrency}",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = FontFamily(Font(R.font.pacifico)),
                        fontWeight = FontWeight(600),
                        color = MaterialTheme.colorScheme.onBackground,
                        letterSpacing = 0.26.sp,
                        textAlign = TextAlign.Center
                    )
                )
            } else {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        viewModel.onEvent(ExchangeEvent.OnExchangeCurrencies)
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Exchange",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.pacifico)),
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.secondary,
                            letterSpacing = 0.26.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Destination
fun ExchangeScreen2() {

    Text(text = "Exchanger 2")

    LazyColumn(
        Modifier
            .fillMaxSize()
    ) {
        items(50) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.DarkGray, RoundedCornerShape(12.dp))
                    .border(
                        width = Dp.Hairline,
                        color = Color.White.copy(alpha = .5f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
            ) {
                AsyncImage(
                    model = "https://source.unsplash.com/random?neon,$it",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
@Destination
fun ExchangeScreen3() {

    val pointsData: List<Point> =
        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))

    val steps = 5

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Blue)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Red)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            ((i * yScale).toString())
        }
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )

    Column {
        Text(text = "Exchanger 3")

        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )

        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )

        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )
    }
}
