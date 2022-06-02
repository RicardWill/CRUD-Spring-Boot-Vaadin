# Aplicación WEB con Java Spring Boot & Vaadin

Este proyecto es una recreación de una problemática dado utilizando la tecnología Vaadin con Spring Boot.

## ¿Como ejecuto la aplicación?

El proyecto es un proyecto Maven estándar. Para ejecutarlo desde la línea de comandos,
escriba `mvnw` (Windows), o `./mvnw` (Mac y Linux), luego abra
http://localhost:8080 en el navegador de su preferencia.

También puede importar el proyecto a su IDE de elección como lo haría con cualquier
Proyecto Mave. Obtenga más información consultando el siguiente enlace (https://vaadin.com/docs/latest/flow/guide/step-by-step/importing) 
Para los siguientes IDE's(Eclipse, IntelliJ IDEA, NetBeans y VS Code).

## Estructura del proyecto
- `MainLayout.java` en `src/main/java` contiene la configuración de navegación (es decir, la
   barra lateral/superior y el menú principal). Esta configuración utiliza
   [Diseño de la aplicación](https://vaadin.com/components/vaadin-app-layout).
- El paquete `views` en `src/main/java` contiene las vistas Java del lado del servidor de su aplicación.
- La carpeta `views` en `frontend/` contiene las vistas JavaScript del lado del cliente de su aplicación.
- La carpeta `themes` en `frontend/` contiene los estilos CSS personalizados.

## Enlaces útiles

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training](https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- View use case applications that demonstrate Vaadin capabilities at [vaadin.com/examples-and-demos](https://vaadin.com/examples-and-demos).
- Discover Vaadin's set of CSS utility classes that enable building any UI without custom CSS in the [docs](https://vaadin.com/docs/latest/ds/foundation/utility-classes). 
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/platform).

## Problemática
La empresa MonteCasino se dedicada al soporte de cámaras de seguridad, actualmente presenta problemas con sus empleados 
no  realizan un chequeo de entrada y salida laboral, para ello ha contratado un equipo de desarrollo no mayor a 5 per-
sonas de profesionales en el desarrollo de software y aplicaciones móviles para realizar el desarrollo de una aplicaci-
ón móvil que cumpla con las siguientes características:

-> El aplicativo debe someterse a los siguientes requerimientos tecnológicos:
-Base de datos en MySQL.
-Interfaz capaz de funcionar en equipos windows, linux, Mac, iOS y android.
-Lenguaje de programación Java.
-La interfaz debe de poder ajustarse a la pantalla del dispositivo en que se acceda.

-> Contar con una interfaz para realizar el chequeo de entrada del empleado con los siguientes datos:
-Clave de empleado.
-Fecha y hora.
-Identificador del kiosko.

-> Contar con una interfaz para realizar el chequeo de salida del empleado con los siguientes datos:
-Clave de empleado.
-Fecha y hora.
-Identificador del kiosko.

-> Generar un reporte visual de las entradas realizadas.

-> Generar un reporte visual de las salidas realizadas.

## Detalles de ejecución
-Vaadin 23.0.9
-IntelliJ IDEA 2022.1.1 (Community Edition)
-Java Development Kit v18
Build #IC-221.5591.52, built on May 10, 2022
Runtime version: 11.0.14.1+1-b2043.45 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 10 10.0
GC: G1 Young Generation, G1 Old Generation
Memory: 992M
Cores: 4
Non-Bundled Plugins:
    dev.eltonsandre.intellij.spring.assistant.plugin (1.5.2)

Kotlin: 221-1.6.21-release-337-IJ5591.52

