package service.impl;

import service.LoginService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

@ApplicationScoped
@Named("login")
public class LoginServiceImpl implements LoginService {
    /**
     * Retrieves the username stored in cookies from the HTTP request.
     *
     * @param req the HttpServletRequest object containing the HTTP request.
     * @return an Optional object containing the username if present in the cookies, or empty if not.
     */
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies(): new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

}
