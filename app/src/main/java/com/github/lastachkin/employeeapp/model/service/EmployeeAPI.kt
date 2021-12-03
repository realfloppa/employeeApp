package com.github.lastachkin.employeeapp.model.service

import com.github.lastachkin.employeeapp.model.entity.EmployeeResponse
import com.github.lastachkin.employeeapp.util.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("users")
    fun getEmployees(): Observable<EmployeeResponse>

    companion object{
        fun create() : EmployeeAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .baseUrl(Constants.API_BASE_URL)
                    .build()

            return retrofit.create(EmployeeAPI::class.java)
        }
    }
}