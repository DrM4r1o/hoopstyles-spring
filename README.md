# HoopStyles with SpringBoot

# Introducción

Bienvenido a la documentación del proyecto "HoopStyles," una tienda de ropa en línea que está diseñada para satisfacer las necesidades de los amantes del baloncesto. En esta documentación, exploraremos la estructura, tecnologías y funcionalidades clave de esta plataforma en línea, que combina moda y estilo con la pasión por el baloncesto.

## Descripción del Proyecto

"HoopStyles" es una tienda virtual dedicada a ofrecer una amplia gama de ropa y accesorios de moda diseñados específicamente para jugadores de baloncesto y aficionados a este emocionante deporte. Nuestra plataforma proporciona a los usuarios la oportunidad de adquirir productos de alta calidad que les permitan expresar su amor por el baloncesto a través de la moda. Ya sea que estés buscando camisetas, zapatillas, sudaderas o cualquier otro artículo relacionado con el baloncesto, "HoopStyles" tiene algo para todos.

## Tecnologías Utilizadas

Para llevar a cabo este proyecto, he migrado a un conjunto de tecnologías basadas en Java con Spring Boot, Hibernate, H2 Database y Redis, este ultimo dockerizado para facilitar la gestión y escalabilidad del sistema. A continuación, se detallan las nuevas componentes:

- **Spring Boot**: Esta plataforma facilita el desarrollo de aplicaciones Java, proporcionando un entorno de ejecución simplificado y permitiendo la creación de aplicaciones robustas de manera eficiente.
- **Hibernate**: Como marco de mapeo objeto-relacional (ORM), Hibernate se encarga de la interacción entre la aplicación y la base de datos H2, facilitando el almacenamiento y recuperación de datos de manera eficiente.
- **H2 Database**: Este sistema de gestión de bases de datos relacional en memoria se utiliza para almacenar y administrar la información de productos, pedidos y usuarios. Al ser liviano y rápido, contribuye a una experiencia de compra fluida.
- **Redis**: Dockerizado para gestionar las sesiones, Redis es un sistema de almacenamiento en memoria que mejora la eficiencia y la velocidad de acceso a datos temporales, como la gestión de sesiones de usuarios en una tienda en línea.
- **Thymeleaf, HTML, CSS y JavaScript**: Para el desarrollo del frontend, hemos adoptado Thymeleaf, un motor de plantillas para Java, junto con HTML, CSS y JavaScript. Thymeleaf facilita la creación de páginas web dinámicas en el servidor, mientras que CSS y JavaScript se utilizan para el diseño y la interactividad del frontend, mejorando la experiencia de usuario en la tienda en línea.

Esta nueva arquitectura proporciona un entorno más moderno y escalable para el desarrollo y despliegue de la tienda en línea, aprovechando las ventajas de las tecnologías Java y los contenedores Docker para una gestión eficiente del sistema.

## Objetivos del Proyecto

El objetivo principal de "HoopStyles" es proporcionar a los amantes del baloncesto una plataforma en línea donde puedan encontrar productos de moda que reflejen su pasión por el deporte. Entre los objetivos específicos del proyecto se incluyen:

1. Ofrecer una amplia variedad de productos de alta calidad relacionados con el baloncesto.
2. Facilitar a los usuarios la navegación y búsqueda de productos de manera intuitiva y eficiente.
3. Permitir a los clientes realizar pedidos de forma segura y sencilla.
4. Proporcionar información detallada sobre productos, incluyendo imágenes, descripciones y precios.
5. Ofrecer un carrito de compras y opciones de pago seguras para completar la experiencia de compra en línea.
6. Zona de administrador para gestionar los distintos productos y categorías que se muestran en la tienda además de monitorización de las ventas.

En esta documentación, exploraremos cada uno de estos aspectos en detalle, desde la arquitectura tecnológica hasta las funcionalidades clave.

## Zona de Administrador

Dentro del área de administración de la aplicación, los usuarios con privilegios de administrador tienen la capacidad de llevar a cabo diversas acciones para gestionar eficientemente la tienda en línea. A continuación, se describen las principales funcionalidades disponibles en la zona de administración:

1. **Gestión de Productos:**
    - **Crear/Modificar/Eliminar Productos:** Los administradores tienen la capacidad de agregar nuevos productos a la tienda, actualizar información existente o eliminar productos que ya no estén disponibles. Esto permite mantener actualizado y variado el catálogo de productos.
2. **Gestión de Categorías:**
    - **Añadir/Eliminar Categorías:** La zona de administración permite la creación y eliminación de categorías de productos. Esto facilita la organización y clasificación de los productos en la tienda, proporcionando una navegación más intuitiva para los clientes.
3. **Monitorización de Ventas:**
    - **Seguimiento de Ventas:** Los administradores pueden acceder a herramientas de monitorización que les proporcionan información detallada sobre las ventas realizadas. Estas herramientas incluyen informes y estadísticas que permiten evaluar el rendimiento de la tienda, identificar tendencias y tomar decisiones informadas para mejorar la eficacia del negocio.

Esta zona de administración centraliza las funciones críticas para la gestión efectiva de la tienda en línea, brindando a los administradores el control necesario para mantener un catálogo actualizado, una organización coherente de productos y una visión clara del rendimiento de las ventas. Estas herramientas son fundamentales para tomar decisiones estratégicas y garantizar el éxito continuo de la tienda en línea.

![imagen](https://github.com/DrM4r1o/hoopstyles-spring/assets/146061528/fbce4b9c-8be0-470c-b7d7-d4402a25c907)


## Despliegue de la aplicación

Para llevar a cabo el despliegue de la aplicación, sigue estos pasos:

1. **Clonación del Repositorio:**
    - Clona el repositorio de la aplicación en la carpeta deseada utilizando el siguiente comando en la terminal:
        
        ```
        git clone <URL_del_repositorio> nombre_de_carpeta
        ```
        
- **Despliegue del Contenedor de Redis:**
    - Asegúrate de tener Docker instalado y ejecuta el siguiente comando para desplegar el contenedor de Redis y exponer el puerto necesario:
        
        ```
        docker run -d --name myredis -p 6379:6379 redis
        ```
        
    
    Este comando inicia un contenedor llamado "myredis" que ejecuta Redis y mapea el puerto 6379 del contenedor al puerto 6379 del host.
    
- **Ejecución de la Aplicación con Maven:**
    - Con el contenedor de Redis funcionando, ejecuta el siguiente comando Maven para lanzar la aplicación Spring Boot:
        
        ```
        mvn spring-boot:run
        ```
        

- **Reinicio de la Aplicación:**
    - Después de la primera ejecución, se recomienda reiniciar la aplicación inmediatamente. Esto asegura la correcta carga y disponibilidad de todos los productos en la tienda en línea. Puedes reiniciar la aplicación de la siguiente manera:
        
        ```
        # Presiona Ctrl + C para detener la aplicación
        # Vuelve a ejecutar la aplicación
        mvn spring-boot:run
        ```
        

- **Acceso a la Aplicación:**
    - Una vez que la aplicación se ha iniciado correctamente, accede a ella a través del navegador web ingresando la siguiente dirección:
        
        ```
        localhost:9000
        ```
        
    
    Esto te llevará a la interfaz de la aplicación, donde podrás interactuar con las funcionalidades implementadas, incluida la gestión de productos, categorías y la monitorización de ventas desde la zona de administración.
    

Este procedimiento garantiza que la aplicación se ejecute correctamente, con la gestión de sesiones respaldada por el contenedor de Redis. Recuerda reiniciar la aplicación después de la primera ejecución para asegurar la correcta visualización de todos los productos. Ten en cuenta que estos son pasos generales y pueden requerir ajustes según las necesidades específicas del entorno de despliegue.
