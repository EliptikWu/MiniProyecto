package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import service.LoginService;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;
@ApplicationScoped
@Named("loginSession")
public class LoginServiceSessionImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
