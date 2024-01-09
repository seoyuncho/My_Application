package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    protected  void onCreate( Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_splash);

        //splash 3초 동안 뜨게 한다.
        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 2000); //3000은 3초란 뜻이다. 1000은 1초다.
        //postDelayed 메서드를 통해 3초 뒤에 SplashHandler 작동하도록 설정
    }

    //SplashHandler 클래스 생성
    private class splashHandler implements Runnable {
        public  void run() {
            startActivity(new Intent(getApplication(), LoginActivity.class));
            SplashActivity.this.finish();
        }
    }

}