package trungndph39729.fpoly.lab7.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitService() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val MyApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}