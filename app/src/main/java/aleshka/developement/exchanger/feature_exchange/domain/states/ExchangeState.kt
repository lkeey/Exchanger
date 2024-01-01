package aleshka.developement.exchanger.feature_exchange.domain.states

data class ExchangeState (
    val amount: Float = 0f,

    val toCurrency: String = "RUB",
    val fromCurrency: String = "USD",

    val result: Float = 0f
)
