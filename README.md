# eBookie

eBookie는 사용자가 다양한 전자책을 쉽게 탐색하고, 구매하며, 다운로드할 수 있는 종합적인 전자책 플랫폼입니다.

![eBookie 스크린샷](./docs/preview.png)

## 🚀 주요 기능

- **🔍 스마트 검색 기능**: 제목, 작가, 카테고리로 도서 찾기
- **💳 안전한 결제 시스템**: PortOne 결제 게이트웨이 연동
- **📱 반응형 디자인**: 데스크톱과 모바일에서 원활한 사용자 경험
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
git clone https://github.com/taewookim02/ebookie.git
cd ebookie
```

### 백엔드 설정
1. MySQL 데이터베이스 구성:
   - MySQL이 3306 포트에서 실행 중인지 확인
   - "root" 이름과 "1234" 비밀번호를 가진 사용자 생성
   - "ebookie"라는 이름의 데이터베이스 생성
2. env.properties에 JWT 등의 시크릿 키 설정
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

