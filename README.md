# Prueba Técnica Microservicios TaxisLibres

El proyecto se enfoca en la creación de entidades de User y Bills. La entidad User permite realizar operaciones de registro y autenticación, generando un token de acceso que habilita el uso de diversos endpoints. Además, se puede acceder a las facturas creadas, identificar las facturas asociadas a un usuario y ver información detallada de una factura específica. Las entidades User y Bills cuentan con operaciones básicas de CRUD (Crear, Leer, Actualizar y Eliminar). El proyecto se ha implementado con medidas de seguridad basadas en JSON Web Tokens (JWT) para garantizar un acceso controlado y seguro.

## Contenido

- [Requisitos Previos](#requisitos-previos)
  El proyecto se desarrolló utilizando Java 17, MySQL y Spring Boot.
- [Instalación](#instalación)
  El repositorio incluye un archivo Docker Compose que simplifica la instalación al crear contenedores para la base de datos y el proyecto.
- [Documentación de API](#documentación-de-api)
  La documentación de la API se generó utilizando Swagger UI y está disponible en http://localhost:8080/swagger-ui/index.html#/ para ayudarte a entender y utilizar los endpoints de la aplicación.

## Requisitos Previos

Para ejecutar este proyecto, debes contar con las siguientes dependencias previamente instaladas:

- **Java 17:** Asegúrate de tener instalada la versión 17 de Java en tu sistema.
- **MySQL:** Necesitarás tener un servidor MySQL en funcionamiento para que el proyecto pueda almacenar y gestionar los datos.
- **Spring Boot:** Este proyecto está basado en Spring Boot, por lo que asegúrate de tenerlo configurado correctamente.

Verifica que todas estas dependencias estén instaladas y funcionando de manera adecuada antes de continuar con la instalación.

## Instalación

Sigue estos pasos para instalar y ejecutar el proyecto:

1. Clona el repositorio desde GitHub usando el siguiente comando:

   ```shell
   git clone https://github.com/StephanyCS1/taxislibres-prueba.git



