# 📚 Desafío 2 - API REST de Alumnos y Materias

Este proyecto fue desarrollado como parte del Segundo Desafío Práctico de la materia **Desarrollo de Aplicaciones con Web Frameworks** en la Universidad Don Bosco. Su objetivo es crear una API RESTful con Spring Boot para gestionar entidades de **Alumno** y **Materia**.

---

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.1+**
- **Spring Data JPA**
- **Base de datos H2 (en memoria)**
- **Lombok**
- **MapStruct**
- **Maven**

---

## 🛠 Cómo ejecutar el proyecto

1. Clona el repositorio o descarga el proyecto.
2. Abre una terminal en la raíz del proyecto.
3. Ejecuta el siguiente comando:

```bash
mvn spring-boot:run
```

> ✅ Asegúrate de tener Maven instalado y Java 17 configurado.

---

## 🗃 Acceso a la Consola H2

Una vez que el proyecto esté corriendo, puedes ingresar a la consola web de la base de datos H2 desde:

📎 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Configuración de conexión:
- **JDBC URL:** `jdbc:h2:mem:desafio2db`
- **Usuario:** `sa`
- **Contraseña:** *(dejar vacío)*

---

## 🔌 Endpoints disponibles

### ➕ Crear un alumno

```http
POST /alumnos
```

**Body (JSON):**
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "idMateria": 1
}
```

---

### 📋 Obtener todos los alumnos

```http
GET /alumnos
```

---

### 🔍 Buscar alumno por ID

```http
GET /alumnos/{id}
```

---

### ✏️ Actualizar alumno

```http
PUT /alumnos/{id}
```

**Body:**
```json
{
  "nombre": "Carlos",
  "apellido": "Lopez",
  "idMateria": 2
}
```

---

### 🗑️ Eliminar alumno

```http
DELETE /alumnos/{id}
```

---

## 📌 Notas importantes

- Se debe insertar al menos una `Materia` antes de crear un `Alumno`, ya que el alumno depende del ID de una materia.
- La base de datos es **en memoria**, por lo que los datos se borran al reiniciar la aplicación.
- Puedes usar la consola H2 para consultar, editar o insertar manualmente las tablas `MATERIA` y `ALUMNO`.

---

## 👨‍💻 Autor

- Nombre: *[Tu nombre completo aquí]*
- Universidad Don Bosco
- Ciclo: I/2025