package aleshka.developement.exchanger.feature_top.domain.states

import co.yml.charts.ui.barchart.models.BarData

data class TopState(

    val isLoading: Boolean = true,

    val barData: MutableList<BarData> = mutableListOf(),
    val pointNames: MutableList<String> = mutableListOf(),

    val base: String = "RUB",
    val amountColumns: Int = 5,
    val theHighest: Float = 0f,
)
