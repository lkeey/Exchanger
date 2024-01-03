package aleshka.developement.exchanger.feature_top.domain.view_models

import aleshka.developement.exchanger.feature_top.domain.repositories.LatestRepository
import aleshka.developement.exchanger.feature_top.domain.states.TopState
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Random

class TopViewModel : ViewModel() {

    companion object {
        private const val TAG: String = "ViewModelTop"
    }

    private val repository = LatestRepository()

    private val _state = MutableStateFlow(TopState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getLatest()

            Log.i(TAG, "result - ${result.body()}")

            savePoints(result.body()?.rates ?: throw Exception("no rates"))

        }
    }

    private fun savePoints(
        currencies: Map<String, Float>
    ) {
        val rates = currencies
            .toList()
            .sortedBy {
                (_, value) -> value
            }
            .takeLast(5)

        val barData = mutableListOf<BarData>()
        val names = mutableListOf<String>()

        barData.add(
            BarData(
                Point(
                    -1f,
                    1f
                ),
                Color(0, 0, 0)
            )
        )

        names.add("")

        for (i in 0..< state.value.amountColumns) {
            Log.i(TAG, rates[i].second.toString())

            barData.add(
                BarData(
                    Point(
                        i.toFloat(),
                        rates[i].second
                    ),
                    Color(Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
                )
            )
            names.add(rates[i].first)
        }

        _state.update {
            it.copy(
                isLoading = false,
                barData = barData,
                pointNames = names,
                theHighest = barData.last().point.y
            )
        }

    }

    private suspend fun getLatest() =
        repository.latestCurrencies(
            base = state.value.base
        )

}
