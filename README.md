# JSP로 구현한 [Todo App] demo project

## 1 개요

**Java Server Page(이하 JSP)** 기술의 경우 자체적 제한이 존재하며, 특히 Spring Boot와 결합할 때 훨씬 더 제한된다는 약점이 있습니다. 따라서 View 단의 Template Engine을 적용함에 있어 JSP 보다는 Thymeleaf를 채택하는 것을 더 나은 대안으로 고려해야 합니다. Spring Boot 진영에서도 기본적으로는 View 영역에 JSP를 사용하는 것을 더 이상 권장하지 않고 있으며, 이에따라 최근 Web Application들은 이전의 JSP 기술 보다는 Thymeleaf, Freemarker, Groovy, Mustache 등의 Template Engine들이 더 많이 사용되고 있는 추세입니다.

하지만 실제 프로젝트에서 JSP 기술을 메인 템플릿으로 적용하는 경우가 흔치 않은 추세라지만 아직까지는 JSP 기술을 완전히 배제하기에는 조금은 이른 시점인 것도 사실입니다. 실세계에는 여러가지 상황과 이유들로 인해 최신 기술을 도입하기 어려운 사업들이 생각보다 많이 존재하고 이들 프로젝트에서는 여전히 JSP와 같은 오래된 기술을 요구하고 있습니다. 이를테면 기존의 JSP 기반 레거시(web application)을 업그레이드 하는 프로젝트가 있을 수 있겠죠. 이 경우에는 HTML 페이지 구현을 위한 템플릿 매커니즘으로써 JSP는 여전히 유효하고 최선의 옵션이 될 수 있습니다.

이러한 이유들로 **Spring Boot에 기반하여 JSP를 적용**해야 하는 상황을 상정하고 기초적이면서 간단한 **Todo App(Demo)**을 구현해보았습니다.

![image](/docs/todo-dashboard-pending.png)

### 2 프로젝트의 구성

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

## 4 Todo App 화면

**Todo App**에서 실제로 동작하는 화면의 몇 가지 예시를 첨부합니다.

참고로 **Todo App**은 실제 Production이 아닌 구현 예시를 위한 **demo project**이므로 화면의 구성과 프로세스가 효율적이지 않은 점을 양지하시기 바랍니다. 구현 예시를 위한 약식의 화면 배치일 뿐입니다.

### 4.1 메인 페이지

![image](/docs/todo-home.png)


### 4.2 Todo 관리 페이지

![image](/docs/todo-list.png)


### 4.3 Todo 등록 페이지

![image](/docs/todo-new.png)


### 4.4 모든 할일 페이지

![image](/docs/todo-dashboard-all.png)

## 5 맺음

**Todo App**(`spring-boot-todo-jsp`)은 **Apache 2.0** 라이선스 하에 배포됩니다.

버그 및 이슈에 대한 리포트나 개선에 대한 의견을 환영합니다!

