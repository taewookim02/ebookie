# eBookie

eBookie는 사용자가 다양한 전자책을 쉽게 탐색하고, 구매하며, 다운로드할 수 있는 종합적인 전자책 플랫폼입니다.

![eBookie 플랫폼](https://via.placeholder.com/800x400.png?text=eBookie+%ED%94%8C%EB%9E%AB%ED%8F%BC)

## 🚀 주요 기능

- **📚 다양한 전자책 카탈로그**: 여러 장르의 도서 탐색
- **🔍 스마트 검색 기능**: 제목, 작가, 카테고리로 도서 찾기
- **💳 안전한 결제 시스템**: PortOne 결제 게이트웨이 연동
- **📱 반응형 디자인**: 데스크톱과 모바일에서 원활한 사용자 경험
- **👤 사용자 프로필 관리**: 주문 추적, 환경설정 관리, 즐겨찾기 저장
- **⬇️ 간편한 다운로드 시스템**: 구매한 전자책 즉시 접근
- **🔐 보안 인증**: JWT 기반 안전한 로그인 및 회원가입

## 🛠️ 기술 스택

### 백엔드
- Java 17
- Spring Boot 3.4.2
- Spring Security 6
- Spring Data JPA
- JWT 인증
- MySQL 5.6
- Data Faker (개발용)
- PortOne 결제 통합

### 프론트엔드
- Vue 3 (Composition API)
- Vite (빌드 도구)
- Vue Router (페이지 라우팅)
- Pinia (상태 관리)
- Axios (API 요청)
- Bootstrap (UI 프레임워크)
- Swiper (캐러셀 컴포넌트)
- Vue-toastification (알림)
- Vue3-spinners (로딩 인디케이터)
- Autocomplete-vue (검색 제안)
- Portone/browser-sdk (결제 게이트웨이)
- Phosphor Icons (아이콘 라이브러리)

## 📋 필수 요구사항

- Java 17 이상
- Node.js 16 이상
- MySQL 5.6 이상
- npm 

## 🔧 설치 방법

### 레포지토리 클론
```bash
git clone https://github.com/yourusername/ebookie.git
cd ebookie
```

### 백엔드 설정
1. MySQL 데이터베이스 구성:
   - MySQL이 3306 포트에서 실행 중인지 확인
   - "root" 이름과 "1234" 비밀번호를 가진 사용자 생성
   - "ebookie"라는 이름의 데이터베이스 생성
2. env.properties에 JWT 등의 시크릿 키 설정정
3. IntelliJ에서 Spring Boot 애플리케이션 실행

### 프론트엔드 설정
1. 의존성 설치:
```bash
cd frontend
npm install
```

- 개발 모드:
```bash
npm run dev
```

- 프로덕션 빌드:
```bash
npm run build
```

<!--## 🏗️ 프로젝트 구조

```
 ebookie/
├── backend/                  # Spring Boot 애플리케이션
│   ├── src/main/java/        # Java 소스 코드
│   │   ├── config/           # 설정 클래스
│   │   ├── controller/       # REST 컨트롤러
│   │   ├── domain/           # 도메인 모델 및 레포지토리
│   │   ├── exception/        # 커스텀 예외
│   │   ├── service/          # 비즈니스 로직
│   │   └── util/             # 유틸리티 클래스
│   └── src/main/resources/   # 애플리케이션 속성 및 정적 리소스
├── frontend/                 # Vue 3 애플리케이션
│   ├── public/               # 정적 에셋
│   ├── src/                  # Vue 소스 코드
│   │   ├── assets/           # 이미지, 폰트 등
│   │   ├── components/       # 재사용 가능한 Vue 컴포넌트
│   │   ├── router/           # Vue Router 설정
│   │   ├── store/            # Pinia 상태 관리
│   │   ├── views/            # 페이지 컴포넌트
│   │   └── App.vue           # 루트 컴포넌트
│   └── vite.config.js        # Vite 설정
└── README.md                 # 프로젝트 문서 
```

 ## 🖥️ API 문서

백엔드가 실행 중일 때 API 문서는 `/swagger-ui.html`에서 확인할 수 있습니다. 

## 💳 결제 연동

eBookie는 안전한 거래를 위해 PortOne 결제 게이트웨이를 사용합니다. 연동 내용:

- 다양한 결제 방법 (신용카드, 계좌이체, 모바일 결제)
- 안전한 거래 처리
- 결제 확인 및 검증
- 주문 상태 추적-->

## 🤝 기여하기

1. 레포지토리 포크
2. 기능 브랜치 생성 (`git checkout -b feature/amazing-feature`)
3. 변경 사항 커밋 (`git commit -m 'Add some amazing feature'`)
4. 브랜치에 푸시 (`git push origin feature/amazing-feature`)
5. Pull Request 열기

