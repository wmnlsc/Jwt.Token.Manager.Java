# Token Management System

The **Token Management System** is a robust and flexible Java library for managing JSON Web Tokens (JWTs) and refresh tokens. It simplifies the process of token generation, validation, and secure handling, making it ideal for authentication and authorization systems in modern applications.

---

## Features
- **Generate JWT Access Tokens**: Issue secure tokens for client authentication.
- **Generate Refresh Tokens**: Enable token renewal for long-lived sessions.
- **Generate ID Tokens**: Provide identity information in OpenID Connect flows.
- **Validate Refresh Tokens**: Ensure token integrity and expiration compliance.
- Fully configurable using the `TokenOptionModel`.
- Supports Multi-Factor Authentication (MFA) workflows.
- Includes extensive validation and error handling.

---

## Installation

Include the library in your project via Gradle:

```gradle
dependencies {
    implementation "com.bbhub:Jwt.Token.Manager:1.0.0"
}
```
