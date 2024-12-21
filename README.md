Prueba Técnica Pokémon

Este proyecto es una aplicación compuesta por un **backend** desarrollado en **Spring Boot + Java** y un **frontend** básico creado con **React + JavaScript**.  
Permite consultar información sobre Pokémon utilizando la API pública [PokeAPI](https://pokeapi.co/). Además, incluye un módulo para gestionar datos de entrenadores 
Pokémon mediante una base de datos PostgreSQL.

## Estructura del Sistema

- **Backend:** `poke-api`  
- **Frontend:** `Pokedex`  

El sistema está dividido en dos módulos principales:

### 1. Módulo de Pokémon
- Consultar información de un Pokémon por nombre o ID, incluyendo:  
  - Nombre  
  - Altura  
  - Peso  
  - Habilidades  
  - Especie  
  - Formas posibles  
- Listar las primeras 50 habilidades de los Pokémon, ordenadas alfabéticamente.  

### 2. Módulo de Entrenadores
- Gestión de entrenadores Pokémon mediante una base de datos PostgreSQL. Cada entrenador tiene:  
  - ID único  
  - Nombre  
  - Ciudad de residencia  
  - Lista de Pokémon favoritos  
  - Puntaje  
- Funcionalidades CRUD (Crear, Leer, Actualizar y Eliminar) para entrenadores.

---

## Tecnologías Utilizadas

- **Backend:**
  - Spring Boot (Framework principal)
  - Java (Lenguaje de programación)
  - Spring Data JPA (Manejo de persistencia)
  - PostgreSQL (Base de datos relacional)
  - PokeAPI (Fuente de datos para información de Pokémon)
  - JUnit (Pruebas unitarias)

- **Frontend:**
  - React + JavaScript (Interfaz gráfica básica para el usuario)

---

## Requerimientos Funcionales

### Módulo de Pokémon:
1. Consultar información de un Pokémon (por nombre o ID) con los siguientes datos:  
   - Nombre, Altura, Peso, Habilidades, Especie y Formas.  
2. Listar las primeras 50 habilidades de Pokémon en orden alfabético.

### Módulo de Entrenadores:
1. Base de datos para almacenar información de los entrenadores Pokémon, incluyendo:  
   - ID único, Nombre, Ciudad de residencia, Lista de Pokémon favoritos, Puntaje.  
2. Operaciones CRUD para la gestión de entrenadores.

---

## Requerimientos Técnicos

- **Patrón de diseño:** Modelo Vista Controlador (MVC)  
- **Base de datos:** PostgreSQL  
- **Pruebas unitarias:** Realizadas con JUnit para garantizar el correcto funcionamiento de los servicios  

---


--Instrucciones para Ejecutar

1. Clonar el repositorio:
    git clone  https://github.com/Josue-Rafael-Rojas/pokemon-api.git
    cd repo-pokemon
    

2. Configuración de la base de datos:
    - Asegúrate de tener PostgreSQL instalado y funcionando.
    - Crea una base de datos llamada pokemon_db o modifica el archivo application.yml con los detalles de tu base de datos.

3. Compilar y ejecutar el proyecto:
    - Si estás utilizando Maven, ejecuta el siguiente comando para compilar y ejecutar el proyecto:
    mvn spring-boot:run
    

4. Acceso a la API REST:
    La API estará disponible en http://localhost:8080.

Interfaz Gráfica

Además del backend, se desarrolló una interfaz gráfica sencilla utilizando React y JavaScript. La aplicación permite visualizar la información de los Pokémon y de
los entrenadores de forma interactiva.

Instrucciones para ejecutar la interfaz gráfica:

1. Asegúrate de tener Node.js instalado. Luego, ejecuta el siguiente comando para instalar las dependencias del proyecto:
   node.js
   npm install
  

3. Iniciar el servidor de desarrollo:
    - Ejecuta el siguiente comando:
    npm run dev
  

La interfaz gráfica estará disponible en "http://localhost:5174".

 Notas
- Las pruebas unitarias se realizaron utilizando JUnit y están incluidas en el proyecto.
- El proyecto se puede extender para incluir más funcionalidades o mejoras según los requisitos.
- Se intentó Dockerizar la aplicación pero por temas de tiempo no se pudo completar
