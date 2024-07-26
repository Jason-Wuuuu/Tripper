# Tripper

Tripper is a dynamic, user-friendly web application designed to transform the way travelers plan and experience trips. This project consists of a Spring Boot backend with MongoDB for data storage, and a Next.js frontend for a responsive and interactive user interface.

![Model](https://github.com/Jason-Wuuuu/Tripper/blob/main/tripper_screenshot.png)

## Project Structure

The project is divided into two main folders:

- `tripper-backend`: Contains the Spring Boot (Maven) backend with MongoDB integration
- `tripper-frontend`: Contains the Next.js frontend application

## Prerequisites

Before you begin, ensure you have the following installed:

- Java JDK
- Maven
- Node.js
- npm (usually comes with Node.js)
- MongoDB

## Getting Started

### Backend Setup

1. Navigate to the backend directory:
   ```
   cd tripper-backend
   ```

2. Install dependencies:
   ```
   mvn clean install
   ```

3. Start the Spring Boot application:
   ```
   mvn spring-boot:run
   ```

The backend server should now be running on `http://localhost:8080` (or your configured port).

### Frontend Setup

1. Navigate to the frontend directory:
   ```
   cd tripper-frontend
   ```

2. Install dependencies:
   ```
   npm install
   ```

3. Start the Next.js development server:
   ```
   npm run dev
   ```

The frontend application should now be accessible at `http://localhost:3000`.

---

For more detailed information about the project, please refer to the documentation in each subdirectory.
