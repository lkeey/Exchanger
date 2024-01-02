package aleshka.developement.exchanger.feature_exchange.data.api

import retrofit2.Call
import retrofit2.http.GET

interface CurrenciesApi {
    @GET("api/currencies.json")
    fun currencies(): Call<Map<String, String>>
}
