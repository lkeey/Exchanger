package aleshka.developement.exchanger.feature_exchange.data.models

import com.google.gson.annotations.SerializedName

data class ExchangeResult(
    @SerializedName("result")
    val result: String,
    @SerializedName("time_last_update_utc")
    val lastUpdate: String,
    @SerializedName("conversion_rate")
    val rate: Float,
    @SerializedName("conversion_result")
    val amount: Float,
)
