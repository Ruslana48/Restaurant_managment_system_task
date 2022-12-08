package dao;

import entity.Dish;
import entity.Order;
import exceptions.DbExceptions;

import java.util.List;
import java.util.Map;

public interface DishDao {

    /**
     * Get all dishes from dish table in db
     * @return list of dishes
     */
    List<Dish>getAllDishes() throws DbExceptions;

    /**
     * Get sorted dishes from category using pagination. All from dish table in db
     * @param sortBy sorting String
     * @return list of dishes
     */
    public List<Dish>getSortedDishesOnPage(String sortBy)throws DbExceptions;

    /**
     * Get Dish by Id
     * @param id
     * @return dish
     */
    public Dish getDishById(int id) throws DbExceptions;
}
