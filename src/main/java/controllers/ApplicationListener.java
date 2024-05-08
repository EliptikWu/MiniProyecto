package controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ApplicationListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicia la aplicación!");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Hola a todos desde la application!");
    }
/* 1. El método getAttribute() está disponible únicamente durante la ejecución de una solicitud específica
   y no puede ser compartido entre distintas solicitudes o sesiones. Su utilidad radica en transportar
   información particular de una solicitud a otra dentro del mismo flujo de solicitud-respuesta.
   Por otro lado, getServletContext().getAttribute() está habilitado para toda la aplicación y su
   valor es compartido entre todas las solicitudes y sesiones. Este método es útil para almacenar
   datos globales e información que debe estar disponible de manera global..*/

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el request!");
        sre.getServletRequest().setAttribute("mensaje", "guardando algún valor para el request");
    }
}
/*  2. La diferencia entre ellos radica en que cada vez que se inicia el servidor se crea un contexto,
    lo que implica que cada solicitud generada, ya sea por un cambio en el servlet o simplemente al
    actualizar una página, desencadena una nueva petición. Un ejemplo de esto puede ser la gestión
    de sesiones, donde al entrar al servidor se inicia un temporizador. Cuando este temporizador llega
    a cero, la sesión se cierra automáticamente, ya sea por medidas de seguridad de la aplicación o por
    petición del usuario.*/