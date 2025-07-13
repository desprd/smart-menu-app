# Smart Menu App

Smart Menu App is a full-stack application that generates personalized weekly menus using OpenAI. It consists of a Java Spring Boot backend and a React frontend.

## Project Structure

- **smart-menu-server** – Spring Boot REST API providing menu generation, user authentication and scheduled weekly emails.
- **smart-menu-client** – React + Vite frontend for user interaction and menu display.

## Prerequisites

- **Java 21** (Maven wrapper included)
- **Node.js** (version 18 or newer recommended)
- **PostgreSQL** database

## Environment Variables

### Server (`smart-menu-server`)

Set the following variables to run the backend:

- `DB_URL` – JDBC connection string
- `DB_USER` – database user
- `DB_PASSWORD` – database password
- `JWT_SECRET` – secret used for JWT tokens
- `OPENAI_API_KEY` – API key for menu generation
- `MAIL_USERNAME` and `MAIL_PASSWORD` – credentials for Mailgun SMTP
- `URL_PATH` – base URL used in password reset emails

Configuration keys are defined in `application.properties`:

```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
spring.ai.openai.api-key=${OPENAI_API_KEY}
...
```

### Client (`smart-menu-client`)

The frontend expects `VITE_API_URL` that points to the backend API:

```
const API_URL = import.meta.env.VITE_API_URL;
```

## Running the Project

### Backend

```bash
cd smart-menu-server
./mvnw spring-boot:run
```

### Frontend

```bash
cd smart-menu-client
npm install
npm run dev
```

The frontend will default to `http://localhost:5173` and proxy requests to the API specified by `VITE_API_URL`.

## Building

To produce an executable JAR and Docker image:

```bash
cd smart-menu-server
./mvnw package
```

The resulting JAR can be run with `java -jar target/smart-menu-server-0.0.1-SNAPSHOT.jar`. A `Dockerfile` is also included for containerised deployments.

For the client:

```bash
cd smart-menu-client
npm run build
```

## Features

- User authentication and profile creation
- AI generated menus with detailed recipes
- Weekly menu emails with attached PDF
- React interface with protected routes and Tailwind styling
