package aleshka.developement.exchanger.feature_top.data.api

import aleshka.developement.exchanger.feature_top.data.models.LatestResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LatestCurrenciesApi {
    @GET("{apiKey}/latest/{currency}")
    suspend fun latest(
        @Path("apiKey") apiKey: String,
        @Path("currency") currency: String,
    ): Response<LatestResult>
}
