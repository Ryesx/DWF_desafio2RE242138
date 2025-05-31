# Desafío 3 - Spring Security con JWT

## Integrantes
- [Jose Manuel Reyes Echeverria] - [RE242138]


## Descripción
API REST desarrollada con Spring Boot que implementa autenticación y autorización usando Spring Security con JSON Web Tokens (JWT). El sistema gestiona alumnos y materias con endpoints protegidos, siguiendo la Guía 11 del curso.

## Tecnologías Utilizadas
- **Spring Boot 3.4.4**
- **Spring Security 6**
- **JWT (JSON Web Tokens)**
- **Spring Data JPA**
- **MySQL** (XAMPP)
- **BCrypt** (encriptación de contraseñas)
- **Lombok**
- **MapStruct**
- **Maven**

## Arquitectura del Sistema

### Autenticación y Autorización
- **JWT Stateless**: Tokens firmados para autenticación sin sesiones
- **BCrypt**: Encriptación segura de contraseñas
- **Roles**: Sistema de roles con `ROLE_USER`
- **Filtros personalizados**: Validación automática de tokens en cada petición

### Base de Datos
- **MySQL**: Base de datos principal usando XAMPP
- **JPA/Hibernate**: ORM para mapeo objeto-relacional
- **Tablas**: `user` (autenticación), `alumno`, `materia`

## Funcionalidades

### 🔓 Endpoints Públicos (sin autenticación)
- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/login` - Iniciar sesión y obtener JWT

### 🔒 Endpoints Protegidos (requieren JWT)

#### Alumnos
- `GET /api/alumnos` - Listar todos los alumnos
- `GET /api/alumnos/{id}` - Obtener alumno por ID
- `POST /api/alumnos` - Crear nuevo alumno
- `PUT /api/alumnos/{id}` - Actualizar alumno
- `DELETE /api/alumnos/{id}` - Eliminar alumno

#### Materias
- `GET /api/materias` - Listar todas las materias
- `GET /api/materias/{id}` - Obtener materia por ID
- `POST /api/materias` - Crear nueva materia
- `PUT /api/materias/{id}` - Actualizar materia
- `DELETE /api/materias/{id}` - Eliminar materia

## Configuración

### Base de Datos MySQL (XAMPP)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bdduser?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### JWT Configuration
```properties
jwt.secret=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
jwt.expiration=86400000
jwt.refresh-expiration=604800000
```

### Script SQL (ejecutar en phpMyAdmin)
```sql
CREATE DATABASE IF NOT EXISTS `bdduser` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `bdduser`;

CREATE TABLE IF NOT EXISTS `user` (
    `id_user` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    `firstname` varchar(255) DEFAULT NULL,
    `lastname` varchar(255) DEFAULT NULL,
    `age` int(11) DEFAULT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## Instalación y Ejecución

### Prerrequisitos
- Java 17+
- Maven 3.6+
- XAMPP (MySQL)

### Pasos
1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Ryesx/DWF_desafio2RE242138.git
   ```

2. **Configurar XAMPP**
   - Iniciar Apache y MySQL
   - Crear base de datos `bdduser` usando el script SQL

3. **Configurar base de datos**
   - Ajustar credenciales en `application.properties` si es necesario

4. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

5. **La API estará disponible en**: `http://localhost:8080`

## Uso con Postman

### 1. Registrar usuario
```http
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "usuario.prueba",
  "password": "password123",
  "firstname": "Usuario",
  "lastname": "Prueba",
  "age": 25
}
```

### 2. Iniciar sesión
```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "usuario.prueba",
  "password": "password123"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 3. Usar JWT en endpoints protegidos
```http
POST http://localhost:8080/api/materias
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
  "nombre": "Programación Web con Spring Boot"
}
```

## Video Demostración
https://drive.google.com/file/d/13FPBh7PkgED_RgyxDvAWMfHKTEv5SSKr/view?usp=sharing

## Implementación según Guía 11

### Componentes Desarrollados
- **SecurityConfig**: Configuración de Spring Security con JWT
- **ApplicationConfig**: Configuración de BCrypt y AuthenticationProvider
- **JwtService**: Generación y validación de tokens JWT
- **JwtAuthenticationFilter**: Filtro para interceptar y validar tokens
- **AuthController**: Endpoints públicos de registro y login
- **UserController**: Endpoints protegidos para gestión de usuarios
- **CustomUserDetailsService**: Carga de usuarios desde base de datos

### Adaptaciones Realizadas
- **Integración con proyecto existente**: Los endpoints de Alumno y Materia ahora están protegidos
- **Uso de XAMPP**: Configuración de MySQL según especificaciones de la guía
- **Estructura de DTOs**: Adaptada para el contexto del proyecto académico

## Seguridad Implementada

### Autenticación
- Contraseñas encriptadas con **BCrypt**
- Tokens **JWT firmados** con clave secreta
- **Validación automática** en cada petición

### Autorización
- **Endpoints públicos**: Registro y login
- **Endpoints protegidos**: Todas las operaciones CRUD
- **Roles**: Sistema de roles con `ROLE_USER`

### Buenas Prácticas
- No exposición de contraseñas en respuestas
- Tokens con tiempo de expiración
- Validación de entrada con Bean Validation
- Manejo de errores personalizado

## Estado del Proyecto
- [x] Configuración Spring Security con JWT
- [x] Integración con MySQL usando XAMPP
- [x] Endpoints de autenticación (registro/login)
- [x] Protección de endpoints existentes
- [x] Pruebas exitosas con Postman
- [x] Video demostración
- [x] Documentación completa

## Estructura del Proyecto
```
src/main/java/sv/edu/udb/
├── config/              # Configuraciones de seguridad
│   ├── SecurityConfig.java
│   └── ApplicationConfig.java
├── controller/          # Controladores REST
│   ├── AuthController.java
│   ├── UserController.java
│   ├── AlumnoController.java
│   └── MateriaController.java
├── controller/dto/      # Objetos de transferencia de datos
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   ├── RegisterRequest.java
│   ├── UserDto.java
│   ├── AlumnoRequest.java
│   └── AlumnoResponse.java
├── domain/             # Entidades JPA
│   ├── User.java
│   ├── Alumno.java
│   └── Materia.java
├── repository/         # Repositorios de datos
│   ├── UserRepository.java
│   ├── AlumnoRepository.java
│   └── MateriaRepository.java
├── service/           # Lógica de negocio y JWT
│   ├── JwtService.java
│   ├── JwtAuthenticationFilter.java
│   ├── CustomUserDetailsService.java
│   ├── AlumnoService.java
│   └── AlumnoServiceImpl.java
└── Desafio2Application.java
```

## Créditos
Proyecto desarrollado siguiendo la **Guía 11 - Spring Security con JWT** del curso Desarrollo de Aplicaciones con Web Frameworks, Universidad Don Bosco.