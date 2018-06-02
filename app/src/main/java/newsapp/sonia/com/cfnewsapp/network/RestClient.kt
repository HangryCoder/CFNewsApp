package newsapp.sonia.com.cfnewsapp.network

import io.reactivex.Observable
import newsapp.sonia.com.cfnewsapp.Constants
import newsapp.sonia.com.cfnewsapp.model.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class RestClient {

    companion object {

        private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }

        fun getNewsAPI(): NewsAPI = getRetrofit().create(NewsAPI::class.java)

    }

    interface NewsAPI {
        @GET(Constants.HEADLINES_EXT)
        fun fetchNews(@Query("category") category: String,
                      @Query("from") date: String,
                      @Query("sortBy") sortBy: String,
                      @Query("apiKey") apiKey: String): Observable<NewsResponse>
    }
}