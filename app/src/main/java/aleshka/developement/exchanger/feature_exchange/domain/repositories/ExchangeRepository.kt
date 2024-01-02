package aleshka.developement.exchanger.feature_exchange.domain.repositories

import aleshka.developement.exchanger.feature_exchange.data.api.CurrenciesApi
import aleshka.developement.exchanger.feature_exchange.data.api.ExchangeApi
import aleshka.developement.exchanger.shared.api.RetrofitBuilder
import aleshka.developement.exchanger.shared.data.Constants

class ExchangeRepository {

    private val exchangeInstance = RetrofitBuilder().instance(ExchangeApi::class.java)
    private val currencyInstance = RetrofitBuilder().instance(CurrenciesApi::class.java)

    suspend fun exchangeCurrencies(
        fromCurrency: String,
        toCurrency: String,
        amount: Float,
    ) = exchangeInstance.exchange(
            apiKey = Constants.API_KEY,
            fromCurrency = fromCurrency,
            toCurrency = toCurrency,
            amount = amount
        )

    suspend fun getCurrencies()
        = currencyInstance.currencies()

}
