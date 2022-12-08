package com.example.servlets;

import dao.DAO;
import entity.Client;
import entity.Dish;
import exceptions.DbExceptions;
import mysql.DishDaoSql;
import org.apache.log4j.Logger;
import util.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String filterParam = request.getParameter("filter");
        String filterAttr = (String) session.getAttribute("filter");
        String filter = filterParam == null ? filterAttr == null ? "all" : filterAttr : filterParam;
        session.setAttribute("filter", filter);
        Client client= (Client) session.getAttribute("client");
        try{
            List<Dish> dishes;
            switch (filter){
                case "all":
                    dishes= DAO.getDao().getDishDao().getAllDishes();
                    break;
                case "by-name":
                    dishes=DAO.getDao().getDishDao().getSortedDishesOnPage("by name");
                    break;
                case "by-cost":
                    dishes=DAO.getDao().getDishDao().getSortedDishesOnPage("by price");
                    break;
                case "by-category":
                    dishes=DAO.getDao().getDishDao().getSortedDishesOnPage("by category_id");
                    break;
                default:
                    /////////////////////////
                    throw new IllegalStateException("Unexpected value: " + filter);
            }
            //session.setAttribute("dishes", dishes);
            request.getSession().setAttribute("dishes", dishes);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DishDaoSql dishDao=new DishDaoSql();
        try {
            List<Dish>dishes=dishDao.getAllDishes();
            request.getSession().setAttribute("allDishesForMenu", dishes);
        } catch (DbExceptions e) {
            log.error(Utils.getErrMessage(e));
            throw new RuntimeException(e);
        }
    }
}
