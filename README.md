# JSP로 구현한 [Todo App] demo project

## 1 개요

이 프로젝트는 JSP(Java Server Pages)를 활용하여 사용자 친화적인 To-Do List 웹 애플리케이션을 개발한 것입니다. 기본적인 CRUD 기능(추가, 삭제, 수정, 완료 여부 체크)을 제공하며, 데이터베이스 연동, 세션 및 쿠키 관리, 유효성 검사 등의 기능을 포함합니다.

![image](/docs/todo-dashboard-pending.png)

### 1.1 프로젝트의 구성

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

## 2 Project Setup

### 2.1 Clone 'spring-boot-todo-jsp'

`git` 명령을 사용하여 **spring-boot-todo-jsp** 프로젝트를 `clone` 합니다.

```bash
$ git clone https://github.com/kyungseo/spring-boot-todo-jsp.git
$ cd spring-boot-todo-jsp
```

### 2.2 Build

`spring-boot-todo-jsp` 프로젝트 디렉토리로 이동한 후 다음 명령을 사용하여 프로젝트 전체 module을 `build` 합니다.

```bash
mvn clean install

````

### 2.3 Run & Test

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

## 3 주요 설정 및 관련 코드

### 3.1 Spring Boot에서 JSP를 사용하기 위한 주요 설정

Spring Boot에서 JSP를 지원하기 위한 기본적 dependency는 다음과 같습니다.

* 기본 내장된 서블릿 컨테이너(Tomcat)과 함께 Web Application 실행을 위해 `spring-boot-starter-web` 의존성 추가
* JSP 페이지를 컴파일하고 렌더링할 수 있도록 `tomcat-embed-jasper` 의존성을 추가
* JSP 페이지에 필요한 JSTL 태그 지원을 제공하는 `jstl` 라이브러리 추가

다음 `/spring-boot-todo-jsp/pom.xml`의 내용을 참고하세요.

```xml
    <dependencies>
        <!-- Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- To compile JSP files -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
         </dependency>

        <!-- JSTL -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>

        <!-- Web Continer -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>

         <!-- javax.el -->
        <dependency>
            <groupId>javax.el</groupId>
            <artifactId>javax.el-api</artifactId>
            <version>3.0.0</version>
        </dependency>
    </dependencies>
```

또한 Tomcat과 같은 웹 컨테이너에 Todo App을 배포할 계획이므로 `packaging`은 `war`로 지정하였습니다.

```xml
<packaging>war</packaging>
```

추가로 war 파일이 실행될 수 있도록 `spring-boot-maven-plugin`을 포함해야 합니다.

```xml
<build>
    <finalName>${project.artifactId}</finalName>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <!-- Spring Boot 기동을 위한 Main class 지정 -->
                <mainClass>kyungseo.poc.todo.jsp.TodoApplication</mainClass>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <configuration>
                <warSourceDirectory>src/main/webapp</warSourceDirectory>
                <!-- DD 파일(web.xml) 없이도 war 진행 -->
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>
    </plugins>
</build>
```

일반적인 경우라면 Spring Boot로 독립 실행하기 위해서 `@SpringBootApplication` annotation을 갖는 application class를 생성하면 되지만, **Todo App**을 웹 컨테이너에 배포하기 위해서 다음 코드와 같이 `SpringBootServletInitializer`를 확장하였습니다.

```java
@SpringBootApplication(scanBasePackages = "kyungseo.poc.todo")
public class TodoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TodoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class);
    }

}
```

## Todo App 화면

**Todo App**에서 실제로 동작하는 화면의 몇 가지 예시를 입니다.

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

