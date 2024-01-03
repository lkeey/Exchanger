package aleshka.developement.exchanger.feature_exchange.domain.states

data class ExchangeState (

    val currencies: List<String> = emptyList(),

    val amount: Float = 0f,

    val toCurrency: String = "RUB",
    val fromCurrency: String = "USD",

    val result: Float = -1f,

    val isSearching: Boolean = false,
)
