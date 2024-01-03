package aleshka.developement.exchanger.feature_top.domain.repositories

import aleshka.developement.exchanger.feature_top.data.api.LatestCurrenciesApi
import aleshka.developement.exchanger.shared.api.RetrofitBuilder
import aleshka.developement.exchanger.shared.data.Constants

class LatestRepository {

    private val latestInstance = RetrofitBuilder(Constants.BASE_EXCHANGE_URL).instance(LatestCurrenciesApi::class.java)

    suspend fun latestCurrencies(
        base: String
    ) = latestInstance.latest(
        apiKey = Constants.API_KEY,
        currency = base,
    )
}
