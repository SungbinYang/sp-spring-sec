# 비대면 시험 사이트 제작

## 동기
 * 요즘 covid19로 인한 감염성 질병으로 모든 학생들이 비대면 수업을 진행하고 있다. 대부분의 초중고 학생들은 비대면 시스템이 잘 지켜지는듯 보인다. 또한 잘은 모르지만 전국적으로 같은 시스템을 쓴다고 들었다. 또한 시험도 그 시스템에서 해결이 가능하다고 하였다.
 * 하지만 대학생들은 이 시험 시스템이 미비한 경우가 많으며 불신이 많아 대면시험을 강행한 수업이 많다. 특히 제가 재학했던 학교는 조금 심했었다.
 * 그래서 비대면 시스템을 진행하면 좋을것 같다는 생각이 들었고, 개발에 들어가고 프로토 작업이 완료되었다.

## 기술 스택
  * OS: MAC
  * build: Gradle
  * language: java 11
  * framework: Spring Boot 2.4, Spring Data JPA, Spring Security, lombok
  * DB: mysql 8.0
  * View: html, css, javascript
  * View-Template : Thymleaf, Bootstrap 4.0, jQuery
  * Test: JUnit5, spring test
  * 개발 툴: Intellij

## 상세 설명
  * 기본적으로 Gradle 프로잭트에서 모듈을 분리 하였다.
  * comp : 시험지에 대한 전반적인 서비스 로직이 담겨져있다.
    * paper: 시험지, 문제, 시험지 템플릿, 정답에대한 도메인 모델을 정의하고, 이에 대한 서비스 로직을 정의하였다. 또한 repository를 정의할때 쿼리메소드와 커스텀 쿼리를 작성하였다.
    * paper-user: 학교, 사용자, 권한에 대한 도메인 모델을 정의하고, 학교와 사용자에 대한 서비스 로직을 작성하였다. 단, 여기서 사용자 로직을 2개로 나눠 정의하였는데 하나는 security에서 정의한 UserDetailService를 이용한것이다.
  * server > paper-app : 로그인, 로그인 정책, DB 초기 세팅을 정의한 모듈이다. 또한 각 교수, 사용자, 관리자에 대한 공통 view파일을 정의하였다.
    - 로그인은 일반적으로 세션에 저장하는 것 이외에 rememTokenBasedRememberMe를 사용하여 remember-me토큰을 저장하는 방식으로 지정을 하였다.
  * web: 각 교수, 사용자, 관리자마다의 개별적인 파일이 정의되어있다.
    - site-manager: 관리자에 대한 로그인, 및 그에 대한 기능을 정의해둔 곳이다.
    - site-teacher: 교수에 대한 로그인, 및 그에 대한 기능을 정의해 둔 곳이다.
    - site-study: 학습자에 대한 로그인 및 그에 대한 기능을 정의해 둔 곳이다.

## 구현 화면
  * 메인 페이지
  ![스크린샷 2021-09-04 오후 4 03 10](https://user-images.githubusercontent.com/18282470/132085943-dc563c40-c34c-40f5-952b-9de4d3de30a8.png)
  
  * 학생 로그인
  ![스크린샷 2021-09-04 오후 4 04 45](https://user-images.githubusercontent.com/18282470/132085977-1a86b4ca-dd0e-4c04-8e3b-cab92bebd875.png)
  
  * 학생 회원가입
  ![스크린샷 2021-09-04 오후 4 06 03](https://user-images.githubusercontent.com/18282470/132086008-d844206a-c24b-4887-a442-17daf5648e12.png)
  
  * 학생 로그인 후 화면
  ![스크린샷 2021-09-04 오후 4 06 55](https://user-images.githubusercontent.com/18282470/132086018-24f59577-219b-43c2-a18e-3217a2a4cc85.png)
  
  * 학생 로그인 실패 화면
  ![스크린샷 2021-09-04 오후 4 12 48](https://user-images.githubusercontent.com/18282470/132086178-07e62a5c-0864-465e-a08e-07fbbed707b9.png)

  * 풀어야 할 시험지 리스트
  ![스크린샷 2021-09-04 오후 4 07 23](https://user-images.githubusercontent.com/18282470/132086031-07898a99-d222-4b55-bd92-71a288a50302.png)
  
  * 시험지 화면
  ![스크린샷 2021-09-04 오후 4 08 13](https://user-images.githubusercontent.com/18282470/132086050-c93156ab-9b91-4e8c-a378-c04eb335cc5f.png)
  
  * 답안 작성 후 화면
  ![스크린샷 2021-09-04 오후 4 08 45](https://user-images.githubusercontent.com/18282470/132086066-6a7b68a2-8f4f-45db-ad46-0c8cee3902a1.png)
  
  * 시험 결과 화면
  ![스크린샷 2021-09-04 오후 4 09 32](https://user-images.githubusercontent.com/18282470/132086081-54b9b7cb-b6e1-4056-b626-f700e2df6bd1.png)
  
  * 시험지 결과 리스트
  ![스크린샷 2021-09-04 오후 4 10 13](https://user-images.githubusercontent.com/18282470/132086097-6b94f3f6-34c3-45e5-9b3f-42859d3bacdb.png)
  
  * 교수 로그인
  ![스크린샷 2021-09-04 오후 4 11 01](https://user-images.githubusercontent.com/18282470/132086134-d0b1691f-d569-4a39-beae-0c896cc8f3d7.png)
  
  * 교수 회원가입
  ![스크린샷 2021-09-04 오후 4 11 44](https://user-images.githubusercontent.com/18282470/132086151-e02c0c14-b402-47ef-aade-040361320a38.png)
  
  * 교수 로그인 실패 화면
  ![스크린샷 2021-09-04 오후 4 13 45](https://user-images.githubusercontent.com/18282470/132086202-df45ea18-0244-409f-81d4-b8f32d331402.png)
  
  * 교수 로그인 화면
  ![스크린샷 2021-09-04 오후 4 14 33](https://user-images.githubusercontent.com/18282470/132086233-b9955796-f2d9-4a1d-956f-64469877cf65.png)
  
  * 교수에 속해있는 학생 리스트
  ![스크린샷 2021-09-04 오후 4 15 01](https://user-images.githubusercontent.com/18282470/132086255-d3cf1a21-3258-40bf-8bbf-9fd8e9ea5f47.png)
  
  * 시험지 리스트
  ![스크린샷 2021-09-04 오후 4 18 53](https://user-images.githubusercontent.com/18282470/132086345-90030016-8b5c-44cb-a801-bd539c637386.png)
  
  * 시험지 등록 화면
  ![스크린샷 2021-09-04 오후 4 16 39](https://user-images.githubusercontent.com/18282470/132086297-9415a0df-a607-4c08-96c2-c88779158be2.png)
  
  * 문제 등록 화면
  ![스크린샷 2021-09-04 오후 4 18 03](https://user-images.githubusercontent.com/18282470/132086332-bea4205c-544e-49fc-b86e-77bea35d0b8e.png)
  
  * 시험지 배포 결과 화면
  ![스크린샷 2021-09-04 오후 4 19 45](https://user-images.githubusercontent.com/18282470/132086368-6339e7b6-b662-43c5-9f6c-deff6a5d9bd2.png)
  
  * 시험지 수정 화면
  ![스크린샷 2021-09-04 오후 4 20 47](https://user-images.githubusercontent.com/18282470/132086419-508355c4-1197-4506-a2cf-6451c1e4227e.png)
  
  * 관리자 로그인 화면
  ![스크린샷 2021-09-04 오후 4 21 27](https://user-images.githubusercontent.com/18282470/132086428-c702a477-22e8-4089-974f-0142e9476271.png)
  
  * 관리자 로그인 실패 화면
  ![스크린샷 2021-09-04 오후 4 22 09](https://user-images.githubusercontent.com/18282470/132086444-7a5610a1-a9e3-4a4d-a5a7-6d20c5ec919c.png)
  
  * 관리자 로그인 화면
  ![스크린샷 2021-09-04 오후 4 22 47](https://user-images.githubusercontent.com/18282470/132086463-0ff54a0e-8362-4b2d-84e0-52bf6e983f51.png)
  
  * 관리자 - 학교 리스트
  ![스크린샷 2021-09-04 오후 4 23 19](https://user-images.githubusercontent.com/18282470/132086473-18b5005a-0200-46b3-9344-1944c75b868a.png)
  
  * 관리자 - 학교 등록 및 수정 화면
  ![스크린샷 2021-09-04 오후 4 23 54](https://user-images.githubusercontent.com/18282470/132086489-a901643c-a83d-461b-9aa2-ec9f0c3871af.png)
  
  * 관리자 - 교수 리스트
  ![스크린샷 2021-09-04 오후 4 25 34](https://user-images.githubusercontent.com/18282470/132086540-1d778168-605e-4d78-bcf8-601311acb454.png)
  
  * 관리자 - 학생 리스트
  ![스크린샷 2021-09-04 오후 4 26 07](https://user-images.githubusercontent.com/18282470/132086565-8333faae-f6ac-4b3f-9c34-d6c7aa77c769.png)
  

## 배운점
>> remember-me 토큰을 이용하여 인증을 하고 권한에 대한 설정 부분에 대하 조금씩 알아가는것 같다.
>> 타임리프에서 어떻게 security를 사용하는지에 대해 조금씩 익숙하게 되는것 같다.

## 부족한점
>> 퀴즈부분에서 시간제한을 둬야했었는데 아직 기술적으로 미비해서 해결하지 못했다.
>> 또한 퀴즈 유형을 조금 객관식, o / x, 주관식 형태로 나누면 더 좋을것 같았으며, 학점에 대해 매길수 있도록 하는것도 추가해볼 예정이다.
