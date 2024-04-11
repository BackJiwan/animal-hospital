# 미니 프로젝트 
## 동물병원 관리 시스템

![readme_mockup2](https://github.com/BackJiwan/animal-hospital/assets/95860566/5243cae6-4167-4a19-ad8f-cd33cdd04565)
<br>

## 1. 개발 환경
- 프로그래밍 언어 : Java 
- 사용 라이브러리 : Lombok, Ojdbc, Spock
- 사용 DB : Oracle DB 
- 협업 툴 : Notion, Github
  <br>

## 2. 기술 목표 

### 객체지향 프로그래밍

- 단일 책임 원칙 
  - DTO, Controller, Service 등 하나의 객체는 하나의 역할만 수행할수 있도록 분리함

### 디자인 패턴

- MVC 패턴 
  - 각 도메인에 대해서 Model, View, Controller 로 나누어서 기능 구현 
- Builder 패턴
  - 각 엔티티는 롬복과 빌터패턴을 이용하여서 엔티티를 생성합니다.
- 싱글톤 패턴
  - 직접 DB에 접근하는 유일한 객체인 DataConnectionManager 객체를 싱글톤으로 사용하여, DB연결 인스턴스의 다중 생성을 방지하고, 재사용하여 리소스 사용을 최적화

### 예외 처리

- 사용자 입력을 받는 다양한 상황에서, 의도하지 않은 입력으로 시스템에 문제가 생기는 것을 방지하기 위해, 문자열 가공 및 정제과정을 구현하고 자바가 제공하는 Exception을 활용

### 단위 테스트 

- 테스트코드를 이용하여 DB연결 상황을 가정하고 테스트를 진행

### 외부 API 사용

- 경기도 공공 데이터 포털의 Open API를 연결하여 사용자에게 유용한 정보를 제공합니다. 


<br>

## 3. 프로젝트 설계과정

### 유스케이스 다이어그램
![readme_mockup2](https://github.com/BackJiwan/animal-hospital/assets/95860566/0a44d5c8-810b-4219-8aea-785497550711)
<br>
### 클래스 다이어그램
![readme_mockup2](https://github.com/BackJiwan/animal-hospital/assets/95860566/17af041d-ac0a-40ba-b2b6-c3b581520c48)
<br>
### ER 다이어그램
![readme_mockup2](https://github.com/BackJiwan/animal-hospital/assets/95860566/f601dde4-a8cb-455a-8b52-dc4e47438c28)
<br>

## 4. 개선할 점 

### 관리자 페이지 
- 사용자와 관리자의 역할 구분을 위해서 Member Entity에 Enum 타입의 Role을 추가해서 권한에 따른 행동범위 제약을 구현하면 좋을 것 같다.

### 로그인 기능 
- 회원가입이후 아이디와 비밀번호를 통해서 프로그램 전역에서 인증/인가 기능에 사용하는 로그인 기능이 있으면 좋을 것 같다.

### 추상화
- 인터페이스를 도입하여 추상화를 적극적으로 사용하면 좋을 것 같다.

## 5. 노션 페이지 
- https://intelligent-drill-0f8.notion.site/DB-2-7c4cf0687ac64e21974d4a3eaa05e0b6?pvs=4