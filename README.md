# 🧳 알고알리움

<img src="./wiki/img/logo.png" height="15%" width="15%" />

## == Contents ==

1. [개요](#1-개요)
2. [프로젝트 소개](#2-프로젝트-소개)
3. [주요기능](#3-주요-기능)
4. [프로젝트 실행방법](#4-프로젝트-실행-방법)
5. [기술스택](#5-기술-스택)
   1. [Mobile](#51-front-end)
   2. [Back-End](#52-back-end)
6. [프로젝트 구조도](#6-프로젝트-구조도)
7. [Design](#7-design)
8. [TEAM](#8-team)

<br><br>

## 1. 개요

- 개발 기간: 2023.10.05 ~ 2023.10.05

- 삼성 청년 소프트웨어 아카데미(SSAFY) 빅데이터 추천 프로젝트
  
  `#빅데이터` `#알고리즘문제추천`

<br><br>

## 2. 프로젝트 소개

![intro page](./wiki/gif/intro_page.gif)

🌊 Naming : 알고리즘문제를 의미하는 알고와 알려준다라는 의미를 가진 알리움을 결합. 

- 알고리즘 문제를 사용자에 실력에 맞추어 (강점 문제, 약점 문제, 비슷한 사용자가 푼 문제)로 나누어 알고리즘 문제를 추천해주는 빅데이터 기반 추천 프로젝트

<br>

> 강점 문제란?

- 사용자의 능력에 비해 많이 푼 유형의 문제로, 능력 대비 어려운 문제를 추천

> 약 문제란?

- 사용자의 능력에 비해 적게 푼 유형의 문제로, 능력 대비 쉬운 문제를 추천

> 비슷한 사용자가 푼 문제란?

- 사용자의 능력과 비슷한 다른 사용자들이 많이 푼 문제를 추천

<br><br>

## 3. 주요 기능

### 3.1. 시즌 별 티어 제도

![filtering](./wiki/gif/filtering.gif)

- 8가지의 티어를 두어 시즌 내 특정 포인트 달성 시 승급
- 매 시즌 포인트 초기화
- 이전 시즌 티어는 상징으로 기록됨

<br>

### 3.2. 랭킹 제도

![STT](./wiki/gif/STT.gif)

- 포인트를 기반으로 전체 사용자의 랭킹을 보여줌

<br>

### 3.3. 스탯 제도

![recommend](./wiki/gif/recommend.gif)

- 사용자에게 5개의 스탯 (지혜, 생명, 힘, 운, 카리스마)을 할당
- 지혜 : 푼 문제 수
- 생명 : 최대 연속 문제풀이 횟수
- 힘 : rating 변화
- 운 : 별조각 갯수
- 카리스마 : 정답자 수가 낮은 문제를 맞출 경우

<br>

### 3.4 알고리즘 문제 추천

![detail](/wiki/gif/detail.gif)

- 알고리즘 문제를 사용자에 실력에 맞추어 (강점 문제, 약점 문제, 비슷한 사용자가 푼 문제)로 나누어 알고리즘 문제를 추천

- 해당 문제를 찜할 수 있고 메모 가능

<br>

### 3.5 PC에 문제 띄우기

![detail](/wiki/gif/detail.gif)

- PC 어플리케이션을 실행하고 모바일과 연동 한 후 모바일 문제를 클릭하면 해당 PC에 웹페이지를 띄움
- 어플리케이션은 백그라운드에서도 실행 가능

<br><br>

## 4. 프로젝트 실행 방법

### 4.1. client 실행

1. **원격 저장소 복제**

```bash
$ git clone https://lab.ssafy.com/s08-bigdata-recom-sub2/S08P22D105.git
```

2. **프로젝트 폴더로 이동**

```bash
$ cd frontend
```

3. **필요한 node_modules 설치**

```bash
$ npm install
```

4. **개발 서버 실행**

```bash
$ npm start
```

<br />

### 4.2. server 실행

1. **원격 저장소 복제**

```bash
$ git clone https://lab.ssafy.com/s08-bigdata-recom-sub2/S08P22D105.git
```

2. **프로젝트 폴더로 이동**

```bash
$ cd backend
```

3. **main 메서드 실행하기**

<br><br>

## 5. 기술 스택

### 5.1. Front-End

<img src="https://img.shields.io/badge/Typescript-3178C6?style=for-the-badge&logo=Typescript&logoColor=white"><img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=black"><img src="https://img.shields.io/badge/.ENV-ECD53F?style=for-the-badge&logo=.ENV&logoColor=black"><img src="https://img.shields.io/badge/KAKAO-FFCD00?style=for-the-badge&logo=Kakao&logoColor=black">

<br/>

### 5.2. Back-End

#### API 서버

<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white"><img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white"><img src="https://img.shields.io/badge/KAKAO-FFCD00?style=for-the-badge&logo=Kakao&logoColor=black">

#### 추천 시스템 서버

<img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=Python&logoColor=white"><img src="https://img.shields.io/badge/Django-092E20?style=for-the-badge&logo=Django&logoColor=white"><img src="https://img.shields.io/badge/Pandas-150458?style=for-the-badge&logo=Pandas&logoColor=white"><img src="https://img.shields.io/badge/Selenium-F7931E?style=for-the-badge&logo=Selenium&logoColor=white">

#### DB

<img src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white">

### Deployment

<img src="https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=Ubuntu&logoColor=white"><img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=black"><img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white"><img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=Nginx&logoColor=white">

### Communication

<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"><img src="https://img.shields.io/badge/Gitlab-FC6D26?style=for-the-badge&logo=Gitlab&logoColor=white"><img src="https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=JiraSoftware&logoColor=white"><img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white"><img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"><img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white">

<br/><br/>

## 6. 프로젝트 구조도

```
└─📂backend
    └─📁 api-server
    └─📁 django
└─📂frontend
```

<details>
<summary>Front-End</summary>
<div markdown="1">

```
┗📦src
  ┣ 📂assets
  ┃ ┣ 📂icon
  ┃ ┣ 📂images
  ┃ ┗ 📂lottie
  ┣ 📂components
  ┃ ┣ 📂auth
  ┃ ┣ 📂bookmark
  ┃ ┃ ┣ 📂bookmarkItem
  ┃ ┣ 📂common
  ┃ ┃ ┣ 📂Button
  ┃ ┃ ┣ 📂DetailBtn
  ┃ ┃ ┣ 📂Dropdown
  ┃ ┃ ┣ 📂Footer
  ┃ ┃ ┣ 📂Header
  ┃ ┃ ┣ 📂Loading
  ┃ ┃ ┣ 📂Mic
  ┃ ┃ ┣ 📂Modal
  ┃ ┃ ┗ 📂RadioButton
  ┃ ┣ 📂course
  ┃ ┃ ┣ 📂map
  ┃ ┃ ┣ 📂sideBar
  ┃ ┃ ┃ ┣ 📂folderList
  ┃ ┃ ┃ ┃ ┣ 📂openFolder
  ┃ ┣ 📂Detail
  ┃ ┣ 📂Intro
  ┃ ┃ ┣ 📂main
  ┃ ┃ ┣ 📂main2
  ┃ ┃ ┣ 📂main3
  ┃ ┃ ┣ 📂main4
  ┃ ┃ ┣ 📂main5
  ┃ ┣ 📂personal
  ┃ ┣ 📂search
  ┃ ┣ 📂survey
  ┃ ┃ ┣ 📂Accordion
  ┃ ┃ ┃ ┣ 📂question1
  ┃ ┃ ┃ ┃ ┣ 📂Button
  ┃ ┃ ┃ ┣ 📂question2
  ┃ ┃ ┃ ┃ ┣ 📂dropdown
  ┃ ┃ ┃ ┣ 📂question3
  ┃ ┃ ┃ ┣ 📂question4
  ┃ ┃ ┃ ┣ 📂question5
  ┃ ┃ ┃ ┃ ┣ 📂OptionCards
  ┃ ┃ ┣ 📂SaveBtn
  ┃ ┃ ┗ 📂SurveyTitle
  ┃ ┣ 📂trip
  ┃ ┃ ┣ 📂tripList
  ┃ ┃ ┃ ┣ 📂tripCardItem
  ┃ ┃ ┗ 📂tripRec
  ┃ ┃ ┃ ┣ 📂tripRecCardItem
  ┃ ┗ 📂utils
  ┣ 📂fonts
  ┣ 📂pages
  ┃ ┣ 📂BookmarkPage
  ┃ ┣ 📂DetailPage
  ┃ ┣ 📂ListPage
  ┃ ┣ 📂LoginPage
  ┃ ┣ 📂SearchPage
  ┣ 📂recoil
  ┣ 📂styles
  ┣ 📂types
  ┣ 📜App.tsx
  ┣ 📜index.css
  ┣ 📜index.tsx
```

</div>
</details>

<details>
<summary>Back-End</summary>
<div markdown="1">

```
└─📂 src
    ├─📂 main
    │  ├─📂 java
    │  │  └─📂 com
    │  │      └─📂 dawool
    │  │          └─📂 api
    │  │              ├─📁 code
    │  │              ├─📁 config
    │  │              ├─📁 controller
    │  │              ├─📂 dto
    │  │              │  ├─📁 detailInfo
    │  │              │  └─📁 user
    │  │              ├─📁 entity
    │  │              ├─📁 error
    │  │              ├─📁 jwt
    │  │              ├─📁 repository
    │  │              └─📁 service
    │  └─📂 resources
    │      ├─📁 static
    │      └─📁 templates
└─🐘 build.gradle
└─🐘 settings.gradle
```

</div>
</details>

<br><br>

## 7. Design

### 7.1. 시스템 구조도

![Architecture](./wiki/img/architecture.png)

<br>

### 7.2. API 설계

![API 명세서](./wiki/gif/API_document.gif)

[API 명세서](https://dodo-elice.notion.site/API-6e8c079e046941eaa35c514dc2f22692)

<br><br>

## 8. TEAM

### 8.1. Front-end

<table class="tg">
<thead>
  <tr>
    <th class="tg-0pky">팀원</th>
    <th class="tg-0pky">최예린</th>
    <th class="tg-0pky">이해솜</th>
    <th class="tg-0pky">이지예</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0pky">GitHub</td>
    <td class="tg-0pky">-</td>
    <td class="tg-0pky">-</td>
    <td class="tg-0pky">-</td>
  </tr>
  <tr>
    <td class="tg-0pky">역할 및 담당 기능</td>
    <td class="tg-0pky">
    - 팀장<br>
    - 디자인<br>
    - 취향설문<br>
    - 추천 및 여행지 목록</td>
    <td class="tg-0pky">
    - FE 팀장<br>
    - 컴포넌트 구조 설계<br>
    - 검색<br>
    - 필터링</td>
    <td class="tg-0pky">
    - 회원관리<br>
    - 장소관리<br>
    - API</td>
  </tr>
</tbody>
</table>

<br>

### 8.2. Back-end

<table>
<thead>
  <tr>
    <th class="tg-0pky">팀원</th>
    <th class="tg-0pky">김정은</th>
    <th class="tg-0pky">박희주</th>
    <th class="tg-0pky">이 준</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0pky">GitHub</td>
    <td class="tg-0pky">-</td>
    <td class="tg-0pky">-</td>
    <td class="tg-0pky">-</td>
  </tr>
  <tr>
    <td class="tg-0pky">역할 및 담당 기능</td>
    <td class="tg-0pky">
    - BE 팀장<br>
    - CI/CD<br>
    - 장소관리</td>
    <td class="tg-0pky">
    - 추천 알고리즘<br>
    - API</td>
    <td class="tg-0pky">
    - 회원관리<br>
    - 검색</td>
  </tr>
</tbody>
</table>

<br><br>

## 📒 License

<p>
This software is licensed under the MIT <a href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp" _blank="new">©SSAFY</a>.
</p>
