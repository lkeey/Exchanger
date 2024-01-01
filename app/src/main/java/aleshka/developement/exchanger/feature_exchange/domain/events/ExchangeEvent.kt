package aleshka.developement.exchanger.feature_exchange.domain.events

sealed class ExchangeEvent {

    data class OnChangedAmount(val amount: Float): ExchangeEvent()

    data class OnChangedFromCurrency(val fromCurrency: String): ExchangeEvent()
    data class OnChangedToCurrency(val toCurrency: String): ExchangeEvent()

    data object OnExchangeCurrencies : ExchangeEvent()
}
