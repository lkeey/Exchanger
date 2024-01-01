package aleshka.developement.exchanger.feature_exchange.data.api

import aleshka.developement.exchanger.feature_exchange.data.models.ExchangeResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApi {
    @GET("{apiKey}/pair/{fromCurrency}/{toCurrency}/{amount}")
    suspend fun exchange(
        @Path("apiKey") apiKey: String,
        @Path("fromCurrency") fromCurrency: String,
        @Path("toCurrency") toCurrency: String,
        @Path("amount") amount: Float,
    ): Response<ExchangeResult>
}
