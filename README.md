# MALGN Spring boot Repository Template
이 repository 는 Spring Boot 기반의 프로젝트를 시작하기 위한 템플릿입니다. 이 템플릿은 기본적인 구조와 설정을 포함하고 있어, 새로운 프로젝트를 빠르게 시작할 수 있도록 도와줍니다.

## dependency
- java 21
- malgn-spring-boot-starter
- spring boot 3.5.0
- jpa
- querydsl
- OAuth 2.0 (KeyFlow OAuth 2.0 Authorization Server 연동)
- p6spy
- postgresql jdbc

## Architecture
- Domain Model Pattern
- Hexagonal Architecture

## dir 구조 (예시)
```text
├── docs
│   ├── apis
│   └── domain
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── malgn
    │   │           ├── adapter
    │   │           │   ├── persistence
    │   │           │   │   └── repository
    │   │           │   │       ├── assets
    │   │           │   │           └── query
    │   │           │   │               └── impl
    │   │           │   ├── integration
    │   │           │   ├── security
    │   │           │   └── web
    │   │           │       └── api
    │   │           │           └── v1
    │   │           │               ├── assets
    │   │           │               │   └── dto
    │   │           ├── application
    │   │           │   ├── assets
    │   │           │   │   ├── model
    │   │           │   │   ├── provided
    │   │           │   │   ├── required
    │   │           │   │   └── service
    │   │           ├── configure
    │   │           └── domain
    │   │               ├── assets
    │   └── resources
    │       └── db
    │           └── migration
    └── test
```

* `docs`: 해당 프로젝트에서 사용하는 문서
  * `apis`: API Docs 로 `OpenAPI 3` Spec 을 따름
  * `domain`: 해당 프로젝트에서 사용하는 domain 에 대한 문서
    * entity 관계는 Class Diagram + Entity Relationship Diagram 등 이해할 수 있는 Diagram 을 포함해야한다. 
    * Diagram 표현은 `mermaid` 로 한다. (github 에서 바로 rendering 됨)
    * `glossary.md`: 해당 프로젝트에서 사용하는 용어집


* configure: application 관련 설정
* domain
  * {names}
* application
  * {names}
    * required: 내부와 상호작용하는 ports
    * provided: 외부와 상호작용하는 ports
    * service: provided interface 를 구현한 application service
      * {version}
        * {names}
* adapter
  * web.api
    * {version}
      * {names}
  * integration: 외부 시스템 연동
  * security: security 관련 (설정 제외)


## 주의사항
* 반드시, project name 을 비롯한 모든 module name 변경할 것
* 빌드시 api docs 는 `${build dir}/resources/statics/docs/apis` 에 복사되어 빌드 후 static resource 로 다운로드 가능해짐
  * `swagger-ui` 문서 통합용 
