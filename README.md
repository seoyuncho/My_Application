# Watermelon: 음악을 나누다 🍉🎶

### **소개**
---

Watermelon은 음악을 사랑하는 이들을 위한 소셜 음악 공유 플랫폼입니다. 친구들과 함께 음악을 나누고, 특별한 순간을 공유하세요. 피드를 통해 친구들이 등록한 다양한 플레이리스트를 간편하게 찾아들을 수 있습니다.

---

### 팀원

---

- **하준학** KAIST 전산학부 19학번  <a href="https://github.com/jannagi" target="_blank"><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white"></a>
    
- **조서윤** 고려대학교 컴퓨터학과 22학번  <a href="https://github.com/seoyuncho" target="_blank"><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white"></a>
---

### 개발 환경

---

IDE: **Android Studio**

Front End: **Java**

Back End: **Node.js**

DataBase: **MySQL / MariaDB**

---

# 앱 기능

### Splash

---

- WaterMelon의 로고 화면을 띄우는 스플래시 화면을 만들었습니다. (2초)
    
    ![app_logo.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/2718863e-6186-4d11-a84d-3c5e9b496687/app_logo.png)
    

---

### Login & Register

---

**로컬 로그인, 구글 로그인, 카카오 로그인의 세 가지 방식 구현**

- 회원가입시 아이디, 비밀번호, 이름, 나이 정보 입력
- 로그인해야 앱 이용 가능
    - 비밀번호 입력시 타인에게 노출되지 못하도록 입력 즉시 보이지 않게 함
- 구글 로그인, 카카오 로그인의 경우 휴대폰의 구글, 카카오 정보를 자동으로 불러옴
    - 회원 정보가 있다면 그대로 앱 화면으로 이동
    - 회원 정보가 없다면 회원가입 창으로 이동
- ID를 이용해서 사용자를 식별하고, 중복 회원가입 방지

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/04359db6-c067-42c3-85d2-6023fbd02b86/Untitled.png)

![KakaoTalk_20240110_201517390.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/1b66fab1-47de-40dd-a2cd-e2f2d06f0f25/KakaoTalk_20240110_201517390.jpg)

![KakaoTalk_20240110_202331895.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/0cf5d401-c514-4b95-a7b4-5f3a882b3333/KakaoTalk_20240110_202331895.jpg)

---

### Tab1 | Home

---

********************친구들의 피드를 볼 수 있는 탭********************

- DB에서 각 사용자별 친구를 불러와서 RecyclerView 형식으로 볼 수 있음
- 각 친구의 피드를 누르면 그 친구의 플레이리스트를 볼 수 있음
- 플레이리스트를 클릭하면 Music Player 화면으로 넘어가 노래를 들을 수 있음

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/9a9788d0-88ab-4504-a56b-fcff32467b3f/Untitled.png)

![KakaoTalk_20240110_201517390_04.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/8413d21c-ec82-48a4-b46c-88ad73e71a98/KakaoTalk_20240110_201517390_04.jpg)

![KakaoTalk_20240110_201517390_05.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/93ae5806-ba56-4ba0-9d19-c53a3524f040/KakaoTalk_20240110_201517390_05.jpg)

---

### Tab2 | My Page

---

********************개인별 플레이리스트를 볼 수 있는 탭********************

- DB에서 각 사용자별 플레이리스트 (노래 제목과 가수)를 불러와서 RecyclerView 형식으로 볼 수 있음
- RecyclerView의 각 노래를 클릭하면 Music Player 화면으로 넘어가 노래를 들을 수 있음
- 우측 상단 버튼을 누르면 역시 DB에서 사용자 정보 (아이디, 비밀번호, 이름, 나이) 불러와서 로그인한 아이디의 정보를 볼 수 있음

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/4fb38712-090c-4f8e-91fe-e38ef9e31c65/Untitled.png)

![KakaoTalk_20240110_201517390_02.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/feb34504-9b17-457a-a52f-edf157214def/KakaoTalk_20240110_201517390_02.jpg)

![KakaoTalk_20240110_201517390_01.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/7a15f029-1bad-4727-8a11-32c9eb1a5385/KakaoTalk_20240110_201517390_01.jpg)

---

### Music Player

---

- media player를 이용하여 음악 불러와서 재생 가능
- 재생, 중지, 빨리감기, 뒤로가기 버튼을 통해 음악 감상 가능
- seekBar를 통해 음악의 어느 부분을 듣고 있는지 시각적으로 알 수 있음

![KakaoTalk_20240110_201517390_03.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/f6aaef0a-7446-4842-be5d-bf1d051a3208/KakaoTalk_20240110_201517390_03.jpg)

---

### APK

---

https://drive.google.com/file/d/1F-u7A4WvJAgtZZRo0XMB7ii2q1bok48d/view?usp=sharing

---
