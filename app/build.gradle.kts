plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.android.identity:identity-credential-android:20231002")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.gauravk.audiovisualizer:audiovisualizer:0.9.2")
    implementation("com.karumi:dexter:6.2.3")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Google Play services
    implementation ("com.google.gms:google-services:4.4.0")
    implementation ("com.google.firebase:firebase-auth:22.3.0")
    implementation ("com.google.firebase:firebase-bom:32.7.0")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    implementation("com.kakao.sdk:v2-all:2.11.2") // 전체 모듈 설치, 2.11.0 버전부터 지원
    implementation("com.kakao.sdk:v2-user:2.11.2") // 카카오 로그인
    implementation("com.kakao.sdk:v2-talk:2.11.2") // 친구, 메시지(카카오톡)
    implementation("com.kakao.sdk:v2-story:2.11.2") // 카카오스토리
    implementation("com.kakao.sdk:v2-share:2.11.2") // 메시지(카카오톡 공유)
    implementation("com.kakao.sdk:v2-navi:2.11.2") // 카카오내비
    implementation("com.kakao.sdk:v2-friend:2.11.2") // 카카오톡 소셜 피커, 리소스 번들 파일 포함

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}