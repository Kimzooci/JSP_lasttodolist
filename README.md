# JSP로 구현한 [Todo App] project

## 개요

이 프로젝트는 JSP(Java Server Pages)를 활용하여 사용자 친화적인 To-Do List 웹 애플리케이션을 개발한 것입니다. 기본적인 CRUD 기능(추가, 삭제, 수정, 완료 여부 체크)을 제공하며, 데이터베이스 연동, 세션 및 쿠키 관리, 유효성 검사 등의 기능을 포함합니다.

![image](/docs/todo-dashboard-pending.png)

### 프로젝트의 구성

```
src/main
│
├── java
│    └── kyungseo.poc.todo                    → Backend module
│         ├── common                          → Common module
│         └── jsp                             → Todo App module
│              ├── TodoApplication.java         : @SpringBootApplication
│              └── WebMvcConfig.java            : @Configuration, @EnableWebMvc
│
├── resources
│    ├── i18n                                 → messages
│    ├── static                               → css, js, vendor
│    └── application.properties               → Application Configuration
│
├── webapp
│    └── WEB-INF
│         ├── view                            → Frontend
│         │    ├── common                       : Tiles Layout & Error page
│         │    └── todo                         : Todo App Pages
│         └── web.xml                         → Deployment Descriptor
│
└── pom.xml                                   → Maven POM file

```

## Project Setup

### Clone 'spring-boot-todo-jsp'

`git` 명령을 사용하여 **spring-boot-todo-jsp** 프로젝트를 `clone` 합니다.

```bash
$ git clone https://github.com/kyungseo/spring-boot-todo-jsp.git
$ cd spring-boot-todo-jsp
```

### Build

`spring-boot-todo-jsp` 프로젝트 디렉토리로 이동한 후 다음 명령을 사용하여 프로젝트 전체 module을 `build` 합니다.

```bash
mvn clean install

````

### Run & Test

먼저 `spring-boot-todo-jsp` 디렉토리로 이동한 후 `mvn spring-boot:run` 명령을 실행하도록 합니다.

```bash
mvn spring-boot:run

```

실행 로그의 마지막 라인에 `Started TodoApplication`이 보인다면 서버가 정상 기동된 것입니다.

```
[restartedMain] INFO  k.p.s.web.VueWebApplication - Started VueWebApplication in 10.243 seconds (JVM running for 10.807)
```

Chrome 이나 Edge 등의 Browser 열고 다음 주소로 접속하여 애플리케이션을 테스트할 수 있습니다.

* [http://localhost:8080](http://localhost:8080)
* [http://localhost:8080/h2](http://localhost:8080/h2) - 비번 없이 `sa`로 접속


---

## Todo App 화면

**Todo App**에서 실제로 동작하는 화면의 몇 가지 예시를 입니다.

참고로 **Todo App**은 실제 Production이 아닌 구현 예시를 위한 **demo project**이므로 화면의 구성과 프로세스가 효율적이지 않은 점을 양지하시기 바랍니다. 구현 예시를 위한 약식의 화면 배치일 뿐입니다.


### Todo 관리 페이지

![image](/docs/todo-list.png)


### Todo 등록 페이지

![image](/docs/todo-new.png)


### 모든 할일 페이지

![image](/docs/todo-dashboard-all.png)

## 맺음

**Todo App**(`spring-boot-todo-jsp`)은 **Apache 2.0** 라이선스 하에 배포됩니다.

버그 및 이슈에 대한 리포트나 개선에 대한 의견을 환영합니다!

