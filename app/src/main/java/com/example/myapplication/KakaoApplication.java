package com.example.myapplication;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    private static KakaoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"2bbfe49f3aa4cf2ffe12a2e533679563");
    }
}