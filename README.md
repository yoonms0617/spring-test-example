# 📑 스프링 부트에서 테스트 코드를 작성하는 방법

Spring Boot는 애플리케이션을 테스트할 때 도움이 되는 다양한 유틸리티와 어노테이션을 제공한다.

Spring Boot에서 테스트 모듈은 다음 두 가지 모듈을 제공한다.

- 핵심 항목을 제공하는 `spring-boot-test`
- 테스트에 대한 자동 구성을 지원하는 `spring-boot-test-autoconfigure`

대부분의 경우는 `spring-boot-starter-test`만 사용해도 충분하며 `spring-boot-starter-test`는 Spring Boot 테스트에 사용되는 Starter 패키지이다.

`spring-boot-starter-test`는 JUnit Jupiter, AssertJ, Hamcrest 및 기타 유용한 라이브러리를 제공한다.

### 주요 라이브러리

`spring-boot-starter-test`에 포함되어 있는 주요 라이브러리는 다음과 같다.

- **Junit 5:** Java 애플리케이션의 단위 테스트를 위한 라이브러리로 사실상 표준이다.
- **Spring Test & Spring Boot Test:** Spring Boot 애플리케이션을 위한 유틸리티 및 통합 테스트를 지원한다.
- **AssertJ:** 테스트를 위한 문법을 제공하고 메소드 체이닝을 통해 직관적인 테스트 흐름을 작성할 수 있게 한다.
- **Hamcrest:** JUnit에 사용되는 Matcher 라이브러리로 테스트 표현식을 작성할 때 문맥적으로 자연스럽고 우아한 문장을 만들 수 있도록 도와준다.
- **Mockito:** Mock 객체를 만들고 관리하고 검즐 할 수 있는 방법을 제공한다.
- **JSONassert:**: Json의 Assertion 라이브러리
- **JsonPath:** Json 객체를 탐색하기 위한 표준화된 방법 제공

### 제공되는 어노테이션

Spring Boot는 테스트 목적에 따라 다양한 어노테이션을 제공한다.

#### 통합 테스트

`@SpringBootTest`

#### 단위 테스트

`@WebMvcTest, @DataJpaTest, @RestClientTest, @JsonTest` 등
