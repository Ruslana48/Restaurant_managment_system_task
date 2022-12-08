package filters;

import entity.Client;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * If current user is admin, he/she doesn't have access to user pages
 */
@WebFilter({"/menu"})
public class ClientFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Client client = (Client) request.getSession().getAttribute("client");
        if (client != null && client.getStatus().equals("Manager")) {
            response.sendRedirect("adminPage.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
