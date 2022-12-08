package filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * If user is logged, then he/she doesn't have access to signup or login pages
 */
@WebFilter({"/login", "/register"})
public class LogInSignUpFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getSession().getAttribute("client") != null) {
            response.sendRedirect("menu.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
