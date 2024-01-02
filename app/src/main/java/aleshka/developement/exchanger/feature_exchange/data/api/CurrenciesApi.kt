package aleshka.developement.exchanger.feature_exchange.data.api

import aleshka.developement.exchanger.feature_exchange.data.models.CurrenciesResult
import retrofit2.Response
import retrofit2.http.GET

interface CurrenciesApi {
    @GET("api/currencies.json")
    suspend fun currencies(): Response<CurrenciesResult>
}
