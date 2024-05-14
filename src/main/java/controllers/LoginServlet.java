package controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.ReservationDto;
import mapping.dtos.UserDto;
import mapping.dtos.VehicleDto;
import service.LoginService;
import service.impl.UserServiceImpl;
import service.impl.ReservationServiceImpl;
import service.impl.VehicleServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
    @Inject
    @Named("login")
    private LoginService auth;
    @Inject
    private UserServiceImpl cService;
    @Inject
    private VehicleServiceImpl vService;
    @Inject
    private ReservationServiceImpl rService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            Connection conn = (Connection) req.getAttribute("conn");

            List<UserDto> userDtoList = cService.list();
            getServletContext().setAttribute("userDtoList", userDtoList);

            List<VehicleDto> vehicleDtoList = vService.list();
            getServletContext().setAttribute("vehicleDtoList", vehicleDtoList);

            List<ReservationDto> reservationDtoList = rService.list();
            getServletContext().setAttribute("reservationDtoList", reservationDtoList);

            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Correct login</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Correct login!</h1>");
                out.println(" <h3>Hi " + username + " you have logged in successfully!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry, you are not authorized to enter this page!");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Hi " + cookieOptional.get() + "</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Hi " + cookieOptional.get() + " you have logged in successfully!</h1>");
                out.println("<p><a href='" + req.getContextPath() +
                        "/index.html'>Return</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>Sign off</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }


    }
}