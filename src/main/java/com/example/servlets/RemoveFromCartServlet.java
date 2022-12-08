package com.example.servlets;

import dao.DAO;
import entity.Client;
import exceptions.DbExceptions;
import org.apache.log4j.Logger;
import util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/remove")
public class RemoveFromCartServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client client= (Client) session.getAttribute("client");
        String dishId =request.getParameter("id");
        try (PrintWriter out = response.getWriter()) {
            DAO.getDao().getCartDao().removeDishFromCart(client.getId(), dishId);
            out.println("remove s");
            response.sendRedirect("cart.jsp");
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
