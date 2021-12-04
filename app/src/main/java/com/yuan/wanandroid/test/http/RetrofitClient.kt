package com.yuan.wanandroid.test.http

import com.yuan.wanandroid.test.app.MainApp
import com.yuan.wanandroid.test.utils.logInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
        .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
        .addInterceptor(logInterceptor())
        .cookieJar(MainApp.getInstance().getPersistentCookieJar())
        .build()
    lateinit var retrofit: Retrofit

    private constructor() {

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var instance: RetrofitClient? = null
        get() {
            if (field == null) {
                field = RetrofitClient()
            }
            return field
        }
        @Synchronized
        fun get(): RetrofitClient {
            return instance!!
        }
    }
}