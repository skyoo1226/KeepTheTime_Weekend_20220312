package com.skyoo.keepthetime_weekend_20220312.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "2420a1b2396762750d47f6671e755d2b")
    }
}