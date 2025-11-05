
# Annotations
- @RestControllers
- ResponseBody -- json format me respone send hoga
### Spring Security can support the following right out of the b
1. Username/password authentication (form login)
2. JWT (JSON Web Token) authentication
3. OAuth2 and Open Connect (OIDC) integration
4. Social login (Google, GitHub, Facebook, etc.)
5. LDAP authentication
6. Remember-Me authentication
7. Role-based access control (RAC)
8. Fine-grained permission expressions using SpEL
9. Concurrent session control (limit login sessions)
10. Session timeout and invalidation
11. Built-in CSRF token generation and validation
12. Cross-Origin Resource Sharing (CORS) headers
13. X-Frame-Options for clickjacking protection
14. Custom authentication providers
15. Custom filters and filter chain
### Authentication workflow with jwt
16. ![Screenshot 2025-11-03 at 21.49.52.png](../../../../../../var/folders/zr/k2cbvfwj7dv7vwnjz2k_70p40000gn/T/TemporaryItems/NSIRD_screencaptureui_NDvYlz/Screenshot%202025-11-03%20at%2021.49.52.png)
17. ![Screenshot 2025-11-03 at 21.49.08.png](../../../../../../var/folders/zr/k2cbvfwj7dv7vwnjz2k_70p40000gn/T/TemporaryItems/NSIRD_screencaptureui_T0ZUjX/Screenshot%202025-11-03%20at%2021.49.08.png)


- filter - work before dispatcher servlet
- interceptor - work before controller (request lock, response/request)



```
docker run -d \
--name mongodb \
-p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=admin \
-e MONGO_INITDB_ROOT_PASSWORD=admin123 \
mongo:latest
docker exec -it mongodb mongosh -u admin -p admin123 --authenticationDatabase admin
```


---
- Mapping -- haven't fk  concept
- embedding
- referencing