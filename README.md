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

- WaterMelon의 로고 화면을 띄우는 스플래시 화면 (2초)

---

### Login & Register

---

**로컬 로그인, 구글 로그인, 카카오 로그인의 세 가지 방식 구현**

- 회원가입시 아이디, 비밀번호, 이름, 나이 정보 입력
- 로그인 (아이디와 비밀번호를 입력)해야 앱 이용 가능
- 구글 로그인, 카카오 로그인의 경우 휴대폰의 구글, 카카오 정보를 자동으로 불러옴
    - 회원 정보가 있다면 그대로 앱 화면으로 이동
    - 회원 정보가 없다면 회원가입 창으로 이동
- ID를 이용해서 사용자를 식별하고, 중복 회원가입 방지

---

### Tab1 | Home

---

********************친구들의 피드를 볼 수 있는 탭********************

- DB에서 각 사용자별 친구를 불러와서 RecyclerView 형식으로 볼 수 있음
- 각 친구의 피드를 누르면 그 친구의 플레이리스트를 볼 수 있음
- 플레이리스트를 클릭하면 Music Player 화면으로 넘어가 노래를 들을 수 있음

---

### Tab2 | My Page

---

********************개인별 플레이리스트를 볼 수 있는 탭********************

- DB에서 각 사용자별 플레이리스트 (노래 제목과 가수)를 불러와서 RecyclerView 형식으로 볼 수 있음
- RecyclerView의 각 노래를 클릭하면 Music Player 화면으로 넘어가 노래를 들을 수 있음
- 우측 상단 버튼을 누르면 역시 DB에서 사용자 정보 (아이디, 비밀번호, 이름, 나이) 불러와서 로그인한 아이디의 정보를 볼 수 있음

---

### Music Player

---

- media player를 이용하여 음악 불러와서 재생 가능
- 재생, 중지, 빨리감기, 뒤로가기 버튼을 통해 음악 감상 가능
- seekBar를 통해 음악의 어느 부분을 듣고 있는지 시각적으로 알 수 있음

---

### APK

---
https://drive.google.com/file/d/1F-u7A4WvJAgtZZRo0XMB7ii2q1bok48d/view?usp=sharing
---
