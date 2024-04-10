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

- 다형성
    - 치료내역 조회 메서드는 매개변수 타입에 따라서 다르게 동작합니다.
### 디자인 패턴

- MVC 패턴 
  - 회원,반려동물,치료 엔티티는 MVC 패턴을 구현하기에 적합하도록 적절하게 분리하였습니다.
- Builder 패턴
  - 각 엔티티는 롬복과 빌터패턴을 이용하여서 엔티티를 생성합니다.

### 예외 처리

- 사용자 입력이 범주를 벗어나지 않도록 처리합니다.
### 테스트 주도 개발

- Spock 프레임워크를 이용하여 기능단위로 Mock 테스트를 진행합니다.
<br>
## 3. 프로젝트 설계 과정

### 유스케이스 다이어그램
![readme_mockup2](https://github.com/BackJiwan/animal-hospital/assets/95860566/0a44d5c8-810b-4219-8aea-785497550711)
<br>
### 클래스 다이어그램
![readme_mockup2](https://github.com/BackJiwan/animal-hospital/assets/95860566/17af041d-ac0a-40ba-b2b6-c3b581520c48)
<br>
### ER 다이어그램
![readme_mockup2](https://github.com/BackJiwan/animal-hospital/assets/95860566/f601dde4-a8cb-455a-8b52-dc4e47438c28)
<br>