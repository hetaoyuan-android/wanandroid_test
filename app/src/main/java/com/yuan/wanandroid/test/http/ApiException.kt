package com.yuan.wanandroid.test.http

data class ApiException(var errCode: Int, var errMsg: String) : Exception()