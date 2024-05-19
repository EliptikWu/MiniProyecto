package controllers;

import domain.model.User;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.UserDto;
import mapping.mapper.UserMapper;
import repository.Repository;
import repository.impl.UserRepositoryJdbcImpl;
import service.impl.UserServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "userController", value = "/user-form")
public class UserController extends HttpServlet {

        @Inject
        private UserRepositoryJdbcImpl userRepository;
        @Inject
        private UserServiceImpl service;

        private String message;

        public void init() {
            message = "User";
        }

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>User</h1>");
            out.println(service.list());
            out.println("</body></html>");
        }

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String telephone = req.getParameter("telephone");
            User user = User.builder()
                    .name(name)
                    .email(email)
                    .telephone(telephone).build();
            UserDto userDto = UserMapper.mapFrom(user);
            service.save(userDto);
            System.out.println(service.list());
            List<String> errors = getErrors(name, email, telephone);
            Map<String, String> errorsmap = getErrors2(name, email, telephone);
            service.save(userDto);
            System.out.println(service.list());
            if (errorsmap.isEmpty()) {
                try (PrintWriter out = resp.getWriter()) {

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("    <head>");
                    out.println("        <meta charset=\"UTF-8\">");
                    out.println("        <title>Resultado form</title>");
                    out.println("    </head>");
                    out.println("    <body>");
                    out.println("        <h1>Resultado form!</h1>");

                    out.println("        <ul>");
                    out.println("            <li>Name: " + name + "</li>");
                    out.println("            <li>Email: " + email + "</li>");
                    out.println("            <li>Telephone: " + telephone + "</li>");
                    out.println("        </ul>");
                    out.println("    </body>");
                    out.println("</html>");
                }
            } else {
                req.setAttribute("errors", errors);
                req.setAttribute("errorsmap", errorsmap);

                req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
            }
        }
        private Map<String,String> getErrors2(String name,String email, String telephone) {
            Map<String,String> errors = new HashMap<>();
            if(name==null ||name.isBlank()){
                errors.put("name","El nombre es requerido");
            }
            if(email==null ||email.isBlank()){
                errors.put("email","El email es requerido");
            }
            if(telephone==null ||telephone.isBlank()){
                errors.put("telephone","El telefono es requerido");
            }
            return errors;
        }
        private List<String> getErrors(String name,String email, String semester)
        {
            List<String> errors = new ArrayList<String>();
            if(name==null ||name.isBlank()){
                errors.add("El nombre es requerido");
            }
            if(email==null ||email.isBlank()){
                errors.add("El email es requerido");
            }
            if(semester==null ||semester.isBlank()){
                errors.add("El semester es requerido");
            }
            return errors;
        }
    }
