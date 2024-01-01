package aleshka.developement.exchanger.feature_exchange.domain.repositories

import aleshka.developement.exchanger.feature_exchange.data.api.ExchangeApi
import aleshka.developement.exchanger.shared.api.RetrofitBuilder
import aleshka.developement.exchanger.shared.data.Constants

class ExchangeRepository {
    private val instance = RetrofitBuilder().instance(ExchangeApi::class.java)

    suspend fun exchangeCurrencies(
        fromCurrency: String,
        toCurrency: String,
        amount: Float,
    ) = instance.exchange(
            apiKey = Constants.API_KEY,
            fromCurrency = fromCurrency,
            toCurrency = toCurrency,
            amount = amount
        )

}
