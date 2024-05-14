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
    /**
     * Retrieves the username stored in the session from the HTTP request.
     *
     * @param request the HttpServletRequest object containing the HTTP request.
     * @return an Optional object containing the username if present in the session, or empty if not.
     */
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
