package com.example.servlets;

import dao.DAO;
import entity.Client;
import entity.Dish;
import entity.Order;
import exceptions.DbExceptions;
import mysql.ReceiptDaoSql;
import org.apache.log4j.Logger;
import util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class MakeAnOrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Client client = (Client) request.getSession().getAttribute("client");
        List<Dish> carts = null;
        try {
            carts = DAO.getDao().getCartDao().dishesFromClientCart(client.getId());
            int orderId=DAO.getDao().getCartDao().makeAnOrder(client.getId(), carts);

            //update total price
            int total= (int) request.getSession().getAttribute("total");

            DAO.getDao().getCartDao().setTotalOrderPrice(client.getId(), total,orderId);

            DAO.getDao().getCartDao().cleanCart(client.getId());
            response.sendRedirect("cart.jsp");
            if (client == null) {
                response.sendRedirect("login.jsp");
            }
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
