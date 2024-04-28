package Connection;

import Annotations.MysqlConn;
import Exceptions.ServiceJdbcException;
import Service.LoginService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**@WebFilter("/*")
public class ConnectionFilter implements Filter{

    @MysqlConn
    private Connection conn;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            Connection connRequest = this.conn;
            if (connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            }
            try {
                request.setAttribute("conn", connRequest);
                chain.doFilter(request, response);
                connRequest.commit();
            } catch (ServiceJdbcException e) {
                //connRequest.rollback();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}**/