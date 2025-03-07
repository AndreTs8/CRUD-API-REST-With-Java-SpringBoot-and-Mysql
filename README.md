API de Autenticación con Spring Boot  

Esta API permite registrar usuarios y autenticarlos usando autenticación básica. Es ideal para proyectos pequeños que requieran un sistema de login SIN JWT.

Características:
- Registro de usuarios
- Inicio de sesión
- Autenticación con Spring Security
- Endpoints protegidos
- Probado en Postman 

Tecnologías Usadas
- Java 
- Spring Boot 3
- Spring Security
- Maven 
- MySQL

La API estará disponible en http://localhost:4050 (Se puede cambiar el puerto en el archivo llamado application.properties)

Notas Importantes

    CSRF está deshabilitado en SecurityConfig para facilitar pruebas en Postman.
    Solo hay 2 rutas activas: POST /user y POST /auth/login.
    Esta API se puede extender con JWT para mejorar la seguridad.

Este proyecto es de código abierto y puedes modificarlo según tus necesidades

Desarrollado por AndreTs8