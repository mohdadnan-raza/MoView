# MoView - Movie Review Web Application

MoView is a full-stack web application where users can browse movies, post reviews, and view what others think about different films. It's built with a microservices architecture using Spring Boot for the backend and React for the frontend.

The goal of the project is to demonstrate a scalable system with modular backend services and a clean, user-friendly frontend interface. The app is designed for learning and experimentation, but it's also structured in a way that could be extended into a production-ready system.

---

## Project Structure

### Backend - `MoView-BED/`

This directory contains all the Spring Boot microservices:

- Movie-Service – Manages movie data (CRUD)
- Review-Service – Handles user reviews and ratings
- User-Registration-Service – User registration and basic auth
- moview-api-gateway – Routes client requests to microservices
- naming-server – Eureka server for service discovery
- spring-cloud-config-server – Centralized configuration for all services

All services connect to a MySQL database and communicate via REST APIs.

### Frontend - `moview updating/`

The frontend is a React application with the following structure:

- Built using React.js with functional components
- Calls backend APIs to fetch and display movies and reviews
- Simple user interface with basic styling
- Allows users to browse movies and see reviews

---

## Technologies Used

Backend:
- Java 17
- Spring Boot
- Spring Cloud (Eureka, Config Server)
- MySQL
- REST APIs

Frontend:
- React.js
- Axios (for API calls)
- HTML/CSS

Dev Tools:
- IntelliJ IDEA / VS Code
- Postman (for API testing)
- Git / GitHub

---

## How to Run

### Backend

1. Clone the repository and open the `MoView-BED/` folder in your IDE.
2. Set up MySQL and create required databases for each service.
3. Start the services in the following order:
   - naming-server
   - spring-cloud-config-server
   - user-registration-service
   - movie-service
   - review-service
   - moview-api-gateway
4. Each service will run on a different port (configured in `application.yml` files).

### Frontend

1. Open a terminal and navigate to `moview updating/`.
2. Run `npm install` to install dependencies.
3. Start the frontend using `npm start`.
4. The app will be available on `http://localhost:3000`.

---

## Notes

- This is a work-in-progress and meant as a learning project.
- Services can be extended to include authentication with JWT, better error handling, and more advanced UI features.
- Dockerization and deployment setup can be added later.

---

## About

This project was created by Mohammad Adnan Raza as part of my learning in full-stack development and microservice architecture. I wanted to build something practical, end-to-end, that covers both system design and UI development.

Feel free to explore, fork, or contribute.

Contact: mohdadnanraza03@gmail.com  
GitHub: [github.com/mohdadnan-raza](https://github.com/mohdadnan-raza)  
LinkedIn: [linkedin.com/in/mohdadnanraza](https://linkedin.com/in/mohdadnanraza)
