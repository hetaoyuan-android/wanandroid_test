package com.yuan.wanandroid.test.apiservice

import com.yuan.wanandroid.test.base.BaseResponse
import com.yuan.wanandroid.test.db.bean.User
import com.yuan.wanandroid.test.setting.bean.LogoutResult
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<BaseResponse<User>>


    @GET("user/logout/json")
    fun logout(): Observable<BaseResponse<LogoutResult>>

}