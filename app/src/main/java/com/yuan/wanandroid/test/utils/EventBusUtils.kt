package com.yuan.wanandroid.test.utils

import org.greenrobot.eventbus.EventBus

class EventBusUtils {
    companion object {
        fun register(obj: Any) {
            EventBus.getDefault().register(obj)
        }

        fun unRegister(obj: Any) {
            EventBus.getDefault().unregister(obj)
        }

        fun post(obj: Any) {
            EventBus.getDefault().post(obj)
        }
    }
}