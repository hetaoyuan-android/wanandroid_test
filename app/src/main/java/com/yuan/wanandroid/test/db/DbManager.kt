package com.yuan.wanandroid.test.db

import com.yuan.wanandroid.test.app.MainApp

class DbManager {

    private var userDao: UserDao

    private constructor() {
        val openHelper = DaoMaster.DevOpenHelper(MainApp.getContext(), "user.db")
        val daoMaster = DaoMaster(openHelper.writableDb)
        val daoSession = daoMaster.newSession()
        userDao = daoSession.userDao
    }

    companion object{
        fun getInstance():DbManager {
            return DbManagerHolder.holder
        }
    }

    private object DbManagerHolder {
        val holder = DbManager()
    }

    fun getUserDao(): UserDao {
        return userDao
    }
}