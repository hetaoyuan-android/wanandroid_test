package com.yuan.wanandroid.test.apiservice

import com.yuan.wanandroid.test.base.BaseResponse
import com.yuan.wanandroid.test.db.bean.User
import com.yuan.wanandroid.test.home.bean.Article
import com.yuan.wanandroid.test.home.bean.ArticleResponse
import com.yuan.wanandroid.test.home.bean.Banner
import com.yuan.wanandroid.test.setting.bean.LogoutResult
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<BaseResponse<User>>


    @GET("user/logout/json")
    fun logout(): Observable<BaseResponse<LogoutResult>>

    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<Banner>>>

    @GET("article/top/json")
    fun getTopArticle(): Observable<BaseResponse<List<Article>>>

    @GET("article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Observable<BaseResponse<ArticleResponse>>

}