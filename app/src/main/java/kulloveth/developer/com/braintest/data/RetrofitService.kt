package kulloveth.developer.com.braintest.data

import io.reactivex.Single
import kulloveth.developer.com.braintest.data.models.Quiz
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("quizes")
    fun fetchQuiz(): Single<Response<Quiz>>

    companion object {
        fun getRetrofitInstance(): RetrofitService {
            val retrofit = Retrofit.Builder()
                .baseUrl("// https://quizzies.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }
}