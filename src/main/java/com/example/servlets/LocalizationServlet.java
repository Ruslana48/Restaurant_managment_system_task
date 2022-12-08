package com.example.servlets;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

@WebServlet("/localization")
public class LocalizationServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String []planguage=request.getParameter("language").split("_");
        String language=planguage[0];
        String country=planguage[1];
        Locale locale=new Locale(language, country);
        request.setAttribute("country", locale.getDisplayCountry());

        request.setAttribute("language", request.getParameter("language"));
        response.setContentType("text/html");
        String uri = request.getRequestURI();

        String pageName = (String) session.getAttribute("pageName");

        request.getRequestDispatcher(pageName).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


}
