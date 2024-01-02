package aleshka.developement.exchanger.shared.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder(
    url: String
) {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> instance(service: Class<T>): T {
        return retrofit.create(service)
    }
}
