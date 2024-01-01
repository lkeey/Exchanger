package aleshka.developement.exchanger.feature_exchange.domain.states

data class ExchangeState (
    val amount: Float = 0f,

    val toCurrency: String = "USD",
    val fromCurrency: String = "RUB",
)
