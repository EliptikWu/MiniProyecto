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
        sce.getServletContext().log("start the application!");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Hello everyone from the application!");
    }
/* 1. The getAttribute() method is available only during the execution of a specific request
    and cannot be shared between different requests or sessions. Its usefulness lies in transporting
    particular information from one request to another within the same request-response flow.
    On the other hand, getServletContext().getAttribute() is enabled for the entire application and its
    value is shared between all requests and sessions. This method is useful for storing
    global data and information that must be available globally.*/

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("initializing the request!");
        sre.getServletRequest().setAttribute("mensaje", "saving some value for the request");
    }
}
/*  2. The difference between them is that every time the server starts a context is created,
     which implies that each request generated, whether by a change in the servlet or simply by
     Refreshing a page triggers a new request. An example of this could be the management
     of sessions, whereupon entering the server a timer is started. When this timer arrives
     to zero, the session is automatically closed, either due to application security measures or due to
     user request.*/