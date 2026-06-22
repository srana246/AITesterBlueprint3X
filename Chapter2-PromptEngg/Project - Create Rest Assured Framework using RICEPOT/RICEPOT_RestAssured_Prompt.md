# 🧪 RICE POT Prompt — REST Assured Automation Framework
### Target API: [Restful Booker](https://restful-booker.herokuapp.com/apidoc/index.html)

---

## 🔴 R — ROLE

You are a **Senior QA Automation Architect** with **12+ years of experience** building enterprise-grade API test frameworks for large-scale CRM, ERP, and SaaS platforms.

**Your expertise includes:**
- REST Assured + Java + TestNG + Maven
- Service Object Model (SOM) — the API equivalent of Page Object Model
- CI/CD-ready, production-level code standards
- Design patterns: **Singleton, Builder, Factory**
- Zero-tolerance for bad coding practices (SOLID principles enforced)

---

## 🔵 I — INSTRUCTIONS

1. Analyze **all endpoints** from: `https://restful-booker.herokuapp.com/apidoc/index.html`
2. Design a **complete enterprise REST Assured framework from scratch**
3. Cover **every endpoint** with both **valid** and **invalid** test cases
4. Follow **SOLID principles** — zero bad coding practices
5. Use **TestNG** for test orchestration (groups, listeners, parallel execution)
6. Implement **token-based auth handling** via the `/auth` CreateToken endpoint
7. Generate **ExtentReports** for detailed HTML test reporting
8. Output **only runnable code** — no inline explanations or comments
9. All configuration values must be **externalized** via `config.properties` — zero hardcoded values
10. Framework must be **CI/CD compatible** (Jenkins / GitHub Actions ready)

---

## 🟢 C — CONTEXT

- **Target API Base URL:** `https://restful-booker.herokuapp.com`
- **API Documentation:** `https://restful-booker.herokuapp.com/apidoc/index.html`
- **Language:** Java 11+
- **Build Tool:** Maven
- **Test Framework:** TestNG
- **HTTP Library:** REST Assured
- **Reporting:** ExtentReports
- **Auth Mechanism:** Cookie-based token (`token=abc123`) OR Basic Auth header

### Endpoints to Cover

| # | Endpoint          | Method | Description            |
|---|-------------------|--------|------------------------|
| 1 | `/auth`           | POST   | CreateToken            |
| 2 | `/booking`        | GET    | GetBookingIds          |
| 3 | `/booking`        | POST   | CreateBooking          |
| 4 | `/booking/{id}`   | GET    | GetBooking             |
| 5 | `/booking/{id}`   | PUT    | UpdateBooking          |
| 6 | `/booking/{id}`   | PATCH  | PartialUpdateBooking   |
| 7 | `/booking/{id}`   | DELETE | DeleteBooking          |
| 8 | `/ping`           | GET    | HealthCheck            |

---

## 🟡 E — EXAMPLES

### Expected Project Folder Structure

```
restassured-booker-framework/
├── src/
│   ├── main/java/
│   │   ├── config/
│   │   │   └── ConfigManager.java          ← Singleton config reader
│   │   ├── constants/
│   │   │   └── EndPoints.java              ← All API endpoint constants
│   │   ├── models/
│   │   │   ├── Booking.java                ← Request/Response POJO
│   │   │   ├── BookingDates.java           ← Nested POJO
│   │   │   └── TokenRequest.java           ← Auth POJO
│   │   ├── services/
│   │   │   ├── AuthService.java            ← Token generation + caching
│   │   │   └── BookingService.java         ← All CRUD API call methods
│   │   └── utils/
│   │       ├── RequestSpecUtil.java        ← Base RequestSpecification
│   │       └── ReportManager.java          ← ExtentReports listener
│   └── test/java/
│       ├── base/
│       │   └── BaseTest.java               ← TestNG setup/teardown
│       └── tests/
│           ├── AuthTests.java              ← Auth valid + invalid tests
│           └── BookingTests.java           ← Booking endpoint tests
├── src/main/resources/
│   └── config.properties                   ← All env values (URL, credentials)
├── testng.xml                              ← Suite config, groups, parallel run
└── pom.xml                                 ← Maven dependencies
```

### Example POJO Usage

```java
BookingDates dates = new BookingDates("2024-01-01", "2024-01-10");
Booking booking = new Booking("Jim", "Brown", 150, true, dates, "Breakfast");
```

### Example config.properties

```properties
base.url=https://restful-booker.herokuapp.com
admin.username=admin
admin.password=password123
default.timeout=10000
```

---

## 🟠 P — PURPOSE

To deliver a **production-ready, CI/CD-compatible REST Assured framework** that:

- Can be plugged into **Jenkins / GitHub Actions** pipelines with zero modification
- Supports **parallel test execution** via TestNG
- Has **zero hardcoded values** — everything driven via `config.properties`
- Achieves **100% endpoint coverage** of the Restful Booker API
- Covers **both valid and invalid** test scenarios for every endpoint
- Is **maintainable and scalable** by any enterprise QA team
- Generates a **rich HTML report** via ExtentReports after every run

---

## 🔵 O — OUTPUT FORMAT

Deliver output **file by file**, in this exact order:

| # | File | Purpose |
|---|------|---------|
| 1 | `pom.xml` | Maven deps — RestAssured, TestNG, ExtentReports, Jackson, Lombok |
| 2 | `config.properties` | All env values — base URL, credentials, timeout |
| 3 | `ConfigManager.java` | Singleton config reader |
| 4 | `EndPoints.java` | All API endpoint path constants |
| 5 | `Booking.java` | Jackson-mapped request/response POJO |
| 6 | `BookingDates.java` | Nested POJO for check-in/check-out |
| 7 | `TokenRequest.java` | Auth request POJO |
| 8 | `RequestSpecUtil.java` | Base RequestSpecification (headers, base URI) |
| 9 | `AuthService.java` | Token generation + in-memory caching |
| 10 | `BookingService.java` | All CRUD API call methods |
| 11 | `ReportManager.java` | ExtentReports listener |
| 12 | `BaseTest.java` | TestNG base class — setup, teardown, report hooks |
| 13 | `AuthTests.java` | Valid + invalid auth test cases |
| 14 | `BookingTests.java` | All booking endpoint valid + invalid tests |
| 15 | `testng.xml` | Suite config with groups + parallel execution |

> **Output rule:** Runnable Java code only. No explanations, no inline comments, no markdown inside code blocks.

---

## 🟣 T — TONE

**Technical · Enterprise-grade · Precise · Zero-noise**

Senior architect tone — code speaks for itself.
No hand-holding comments. No filler text.
Every class is production-deployable on day one.

---

## 📋 Step-by-Step Build Plan

| Step | Deliverable | Description |
|------|-------------|-------------|
| **1** | `pom.xml` | Maven dependencies and plugin config |
| **2** | `config.properties` | Externalized environment configuration |
| **3** | `ConfigManager.java` | Singleton pattern config loader |
| **4** | `EndPoints.java` | API path constants |
| **5** | POJOs | `Booking`, `BookingDates`, `TokenRequest` |
| **6** | `RequestSpecUtil.java` | Reusable base spec (content-type, base URI, logging) |
| **7** | `AuthService.java` | Token fetch + cache with thread-safety |
| **8** | `BookingService.java` | Service layer for all 8 endpoints |
| **9** | `ReportManager.java` | ExtentReports HTML report listener |
| **10** | `BaseTest.java` | @BeforeSuite / @AfterSuite / @BeforeMethod hooks |
| **11** | `AuthTests.java` | Auth: valid creds, invalid creds, empty creds |
| **12** | `BookingTests.java` | All booking tests — valid + invalid, CRUD coverage |
| **13** | `testng.xml` | Groups: smoke, regression, negative — parallel config |

> **Process:** Plan first → confirm → build step by step → validate each file before proceeding to the next.

---

*Generated using the RICE POT Prompt Framework*
*Framework: REST Assured + Java + TestNG + Maven + ExtentReports*
*API Under Test: https://restful-booker.herokuapp.com*
