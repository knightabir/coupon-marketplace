# Coupon Marketplace

A robust and scalable coupon marketplace application built with Java Spring Boot, MongoDB, and Vite React. This application allows users to register and list their coupon codes for sale, while buyers can purchase these coupons securely.

## Features

- User registration and authentication (Normal Password Login and Google Login)
- Listing and buying coupon codes
- Secure transactions with wallet management for sellers
- Admin panel to manage users, coupons, and transactions
- JWT-based authorization

## Technologies Used

- **Backend:** Java Spring Boot
- **Frontend:** Vite React
- **Database:** MongoDB
- **Authentication:** Spring Security, JWT, Google OAuth2

## Getting Started

### Prerequisites

- Java 17 or later
- Node.js and npm
- MongoDB

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/coupon-marketplace.git
    cd coupon-marketplace
    ```

2. **Backend Setup:**
    - Navigate to the `backend` directory and build the project:
      ```bash
      cd backend
      ./mvnw clean install
      ```
    - Run the Spring Boot application:
      ```bash
      ./mvnw spring-boot:run
      ```

3. **Frontend Setup:**
    - Navigate to the `frontend` directory:
      ```bash
      cd frontend
      ```
    - Install the dependencies:
      ```bash
      npm install
      ```
    - Run the development server:
      ```bash
      npm run dev
      ```

### Configuration

- Update the `application.properties` file with your MongoDB and OAuth2 credentials:
  ```properties
  spring.data.mongodb.uri=mongodb://localhost:27017/coupon-marketplace
  jwt.secret=YOUR_SECRET_KEY
  jwt.expiration=3600
  spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
  spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
  spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/login/oauth2/code/google
  ```
# API Endpoints
## User APIs

### Register User
```
POST /api/users/register
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "aB3yI@example.com",
  "password": "password123"
}
```
### Login
```
POST /api/users/login
Content-Type: application/json

{
  "email": "aB3yI@example.com",
  "password": "password123"
}
```
### Get User Profile
```
GET /api/users/profile
Authorization: Bearer <jwt_token>
```
### Withdraw Funds
```
POST /api/users/wallet/withdraw
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "amount": 10.00
}
```
## Coupon APIs

### Add Coupon
```
POST /api/coupons
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "code": "COUPON123",
  "description": "10% off on all products",
  "discount": 10.00,
  "expiration": "2024-01-01T00:00:00.000Z"
}
```
### Get All Coupons
```
GET /api/coupons
```
### Get Coupon Details
```
GET /api/coupons/{couponId}
```
### Buy Coupon
````
PUT /api/coupons/{couponId}/buy
Authorization: Bearer <jwt_token>
````
# Admin APIs

### Get All Users
````
GET /api/admin/users
Authorization: Bearer <admin_jwt_token>
````
### Delete User
````
DELETE /api/admin/users/{userId}
Authorization: Bearer <admin_jwt_token>
````
### Get All Coupons
````
GET /api/admin/coupons
Authorization: Bearer <admin_jwt_token>
````
### Delete Coupon
````
DELETE /api/admin/coupons/{couponId}
Authorization: Bearer <admin_jwt_token>
````
### Get All Transactions
````
GET /api/admin/transactions
Authorization: Bearer <admin_jwt_token>
````

## Contributing

We welcome contributions to improve this project! Please follow these steps to contribute:

1. **Fork the repository**:  
   Click the "Fork" button at the top right corner of the repository page.

2. **Create a new branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```
   
3. **Make your changes**:
   - Implement your feature or fix bugs.
   - Ensure your code follows the project's coding standards and conventions.

4. **Commit your changes**:
   ```bash
   git commit -m "Add your message here"
   ```

5. **Push to your forked repository**:
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**:
   - Navigate to the original repository and click "New Pull Request".
   - Provide a detailed description of your changes and submit the pull request for review.

## Code of Conduct

Please read our Code of Conduct to understand how to interact in this project.

## License

This project is licensed under the MIT License.
`````
This README file includes all necessary information to help users get started with your project and provides clear instructions for those who want to contribute. If you need further customization or additional sections, feel free to ask!
`````
Thank you for contributing to the Coupon Marketplace project!