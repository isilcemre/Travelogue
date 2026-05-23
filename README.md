# Travelogue — Travel Journal Web App

A personal travel journal application built with Spring Boot, allowing users to register, log in, and manage their own travel entries with photos.

---

## Features

- User registration and login with session-based authentication
- Each user can only see and manage their own travel entries
- Add travel entries with country, city, description, and a photo
- Delete travel entries
- Update username and password from the profile page
- Photo storage as binary data in the database (LONGBLOB)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 4.0.6 |
| Security | Spring Security (session-based, plain-text passwords) |
| ORM | Spring Data JPA / Hibernate |
| Database | MySQL |
| Templating | Thymeleaf + Thymeleaf Spring Security extras |
| Build Tool | Maven |

---

## Project Structure

```
src/
└── main/
    ├── java/com/example/geziGunlugu/
    │   ├── config/
    │   │   └── SecurityConfig.java       # Spring Security configuration
    │   ├── controller/
    │   │   ├── GeziController.java       # Travel entry CRUD + profile update
    │   │   ├── ImageController.java      # Serving stored photos
    │   │   └── LoginController.java      # Login / register pages
    │   ├── entity/
    │   │   ├── GeziKaydi.java            # Travel entry entity
    │   │   └── Kullanici.java            # User entity
    │   ├── repository/
    │   │   ├── GeziRepository.java       # Travel entry queries
    │   │   └── KullaniciRepository.java  # User queries
    │   ├── service/
    │   │   └── GeziService.java
    │   └── GeziGunluguApplication.java   # Main entry point
    └── resources/
        ├── application.properties
        └── templates/
            ├── index.html                # Landing page
            ├── login.html
            ├── register.html
            ├── form.html                 # Add new entry
            └── list.html                # My travel entries
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL

### Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE gezi_gunlugu_db;
```

### Configuration

Open `src/main/resources/application.properties` and update the credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gezi_gunlugu_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### Running the App

```bash
./mvnw spring-boot:run
```

The app will start on `http://localhost:8080`.

---

## Usage

1. Go to `http://localhost:8080` and click **Register** to create an account.
2. Log in with your credentials.
3. Add travel entries via the **Save** form — enter a country, city, description, and optionally upload a photo.
4. View all your entries on the **My Travels** page.
5. Delete any entry directly from the list.
6. Update your username or password from the **Profile** page.

