package com.yuan.wanandroid.test.utils

import org.greenrobot.eventbus.EventBus

class EventBusUtils {
    companion object {
        public fun register(obj: Any) {
            EventBus.getDefault().register(obj)
        }

        public fun unRegister(obj: Any) {
            EventBus.getDefault().unregister(obj)
        }

        public fun post(obj: Any) {
            EventBus.getDefault().post(obj)
        }
    }
}