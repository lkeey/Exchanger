package aleshka.developement.exchanger.feature_top.data.models

import com.google.gson.annotations.SerializedName

data class LatestResult(
    @SerializedName("result")
    val result: String,
    @SerializedName("time_last_update_utc")
    val lastUpdate: String,
    @SerializedName("conversion_rates")
    val rates: Map<String, Float>,
)
