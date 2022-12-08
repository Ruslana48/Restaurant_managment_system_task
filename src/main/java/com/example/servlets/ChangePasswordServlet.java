package com.example.servlets;

import mysql.ConnectionPool;
import util.SqlUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/newPassword")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        RequestDispatcher dispatcher = null;
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {

            try {
                loadDriver();
                String connectionUrl = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
                try (Connection con = DriverManager.getConnection(connectionUrl, "root", "xhz2woVLxhz2woVL");) {
                    PreparedStatement pst = con.prepareStatement(SqlUtils.CHANGE_PASSWORD);
                    pst.setString(1, newPassword);
                    pst.setString(2, (String) session.getAttribute("email"));

                    int rowCount = pst.executeUpdate();
                    if (rowCount > 0) {
                        request.setAttribute("status", "resetSuccess");
                        dispatcher = request.getRequestDispatcher("login.jsp");
                    } else {
                        request.setAttribute("status", "resetFailed");
                        dispatcher = request.getRequestDispatcher("login.jsp");
                    }
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}