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
import java.util.List;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        Client client = (Client) request.getSession().getAttribute("client");
        int sum=0;
        try {
            List<Dish> allClientDishes= DAO.getDao().getCartDao().dishesFromClientCart(client.getId());
            for (Dish d:allClientDishes){
                int tmp=DAO.getDao().getCartDao().getDishCountInCart(client.getId(), d.getId());
                sum+=tmp*d.getPrice();
            }
            request.getSession().setAttribute("total", sum);
            response.sendRedirect("cart.jsp");
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
            throw new RuntimeException(e);
        }
    }
}
