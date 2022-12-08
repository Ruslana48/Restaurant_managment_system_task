package com.example.servlets;

import dao.DAO;
import entity.Client;
import entity.Dish;
import exceptions.DbExceptions;
import org.apache.log4j.Logger;
import util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cart")
public class AddToCartServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            out.println("cart id");

            Client client = (Client) session.getAttribute("client");
            if(!DAO.getDao().getCartDao().checkWhetherDishExistInClientCart(id, client.getId())) {
                DAO.getDao().getCartDao().addDishToCart(client.getId(), id, 1);
            }

            List<Dish> list=DAO.getDao().getCartDao().selectDishesByClient(client.getId());
            request.getSession().setAttribute("clientDishesList",list);
            response.sendRedirect("menu.jsp");
            //}
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int quantity= Integer.parseInt(request.getParameter("quantity"));
        response.sendRedirect("cart.jsp");
        Client client = (Client) session.getAttribute("client");
        DAO.getDao().getCartDao().updateCount(quantity, client.getId());
    }
}
