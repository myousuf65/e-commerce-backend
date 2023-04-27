# Spring Security JWT - How to implement Symmetric Key Encryption

In a previous [tutorial](https://www.danvega.dev/blog/2022/09/06/spring-security-jwt/) I walked through the basics of how
to secure your REST endpoints with Spring Security and JWts. In that tutorial we used an asymmetric key pair for signing the token and validating the signature. While this is a excellent approach it is a little more complex and one of the questions I keep receiving is how can do this using a shared key. 

In this and the previous tutorial, we're utilizing Spring Security's built-in OAuth2 Resource Server, which utilizes a cryptographic signature to validate tokens, simplifying the process by eliminating the need to call a separate authorization server. Just to recap what we discussed in the previous tutorial about symmetric and asymmetric keys: 

**Symmetric Key:** The same key is used for signing the token and verifying the signature. 
**Asymmetric Key Pair:** One key to sign the token and a different key is used to verify the signature. 

There are pros and cons to each approach but using a shared key can simplify the development process and is faster. A good use case for this is when you have a SPA with a single backend and you want it to be stateless/sessionless (e.g to avoid issues with scaling up / sticky sessions / sharing sessions across instances).

## Running this application 

To run this application from your IDE run the main method in `Application.java` or from a terminal run the command `./mvnw spring-boot:run`. 

Once the application is up and running the first step is to obtain a JWT. To do this you will need to make a `POST` request to the `/api/auth/token` endpoint with basic auth credentials. The default user defined in `SecurityConfig` has a username of `dvega` and a password of `password`.

![Postman POST Request](./images/postman-post.png)

From there you will receive a JWT token which you can use to make a `GET` request to `http://localhost:8080` 
