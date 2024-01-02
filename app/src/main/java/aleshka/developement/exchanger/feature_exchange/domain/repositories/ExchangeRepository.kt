package aleshka.developement.exchanger.feature_exchange.domain.repositories

import aleshka.developement.exchanger.feature_exchange.data.api.CurrenciesApi
import aleshka.developement.exchanger.feature_exchange.data.api.ExchangeApi
import aleshka.developement.exchanger.shared.api.RetrofitBuilder
import aleshka.developement.exchanger.shared.data.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ExchangeRepository {

    private val exchangeInstance = RetrofitBuilder(Constants.BASE_EXCHANGE_URL).instance(ExchangeApi::class.java)
    private val currencyInstance = RetrofitBuilder(Constants.BASE_CURRENCIES_URL).instance(CurrenciesApi::class.java)

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

    fun getCurrencies(
        onSuccess: (List<String>) -> Unit,
        onError: () -> Unit
    ) {

        currencyInstance.currencies().enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                if (response.isSuccessful) {
                    // get map with all currencies and names
                    val currenciesMap: Map<String, String>? = response.body()

                    // save only currencies
                    val currenciesList: List<String> = currenciesMap?.entries?.map {
                        it.key
                    } ?: emptyList()

                    onSuccess(currenciesList)

                } else {
                    onError()
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                onError()
            }

        })
    }


}
