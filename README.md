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
            └── list.html                 # My travel entries
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


##Screenshots

<img width="1067" height="869" alt="travelDetail" src="https://github.com/user-attachments/assets/8bdde083-5008-40d1-80f1-1b83963edd01" />


<img width="1063" height="887" alt="travels" src="https://github.com/user-attachments/assets/66bb6c3c-b1bc-49ea-a3c7-7a104ecd0baf" />


<img width="672" height="951" alt="register" src="https://github.com/user-attachments/assets/420196b1-e2dc-47a9-9382-cd9da390951e" />


<img width="1762" height="883" alt="welcome" src="https://github.com/user-attachments/assets/077d6d42-5b4e-4d86-8e6b-10182d0d0981" />


<img width="1901" height="969" alt="dashboard" src="https://github.com/user-attachments/assets/6dcaed23-9791-403f-a6f7-4ca115c6812b" />


<img width="1117" height="737" alt="search" src="https://github.com/user-attachments/assets/428e77f4-49ba-402e-9609-f6158d463aec" />


<img width="1061" height="695" alt="map" src="https://github.com/user-attachments/assets/1b4fda1b-163e-4249-8d95-6cb844aeeb09" />


<img width="822" height="427" alt="choseCountry" src="https://github.com/user-attachments/assets/3970dc5a-4890-456a-998b-383e8d269e98" />


<img width="462" height="332" alt="profile" src="https://github.com/user-attachments/assets/a4437b7a-54c2-40c4-8ffa-4041cd767414" />


<img width="934" height="968" alt="addTravel" src="https://github.com/user-attachments/assets/e1dabf18-de79-4de7-ba04-59c11de7f99d" />


