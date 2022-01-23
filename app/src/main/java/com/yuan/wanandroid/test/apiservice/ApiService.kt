package com.yuan.wanandroid.test.apiservice

import com.yuan.wanandroid.test.base.BaseResponse
import com.yuan.wanandroid.test.db.bean.User
import com.yuan.wanandroid.test.home.bean.Article
import com.yuan.wanandroid.test.home.bean.ArticleResponse
import com.yuan.wanandroid.test.home.bean.Banner
import com.yuan.wanandroid.test.project.bean.ProjectResponse
import com.yuan.wanandroid.test.project.bean.ProjectTab
import com.yuan.wanandroid.test.search.bean.SearchHot
import com.yuan.wanandroid.test.search.bean.SearchResultResponse
import com.yuan.wanandroid.test.setting.bean.LogoutResult
import com.yuan.wanandroid.test.system.bean.SystemCategory
import com.yuan.wanandroid.test.web.bean.AddFavoriteResponse
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

    @GET("project/list/{page}/json")
    fun getProjectLists(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ProjectResponse>>

    @GET("project/tree/json")
    fun getProjectTabs(): Observable<BaseResponse<List<ProjectTab>>>

    @POST("lg/collect/add/json")
    @FormUrlEncoded
    fun addFavorite(@Field("title") title: String, @Field("author") author: String, @Field("link") link: String): Observable<BaseResponse<AddFavoriteResponse>>

    @POST("lg/collect/{id}/json")
    fun addFavorite(@Path("id") id: Int): Observable<BaseResponse<AddFavoriteResponse>>

    @GET("tree/json")
    fun getSystem(): Observable<BaseResponse<List<SystemCategory>>>

    @GET("article/list/{page}/json")
    fun getSystemArticles(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleResponse>>


    @GET("hotkey/json")
    fun getSearchHot(): Observable<BaseResponse<ArrayList<SearchHot>>>

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    fun getSearchResult(@Path("page") page: Int, @Field("k") keyword: String): Observable<BaseResponse<SearchResultResponse>>


}