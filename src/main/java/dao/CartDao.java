package dao;

import entity.Client;
import entity.Dish;
import exceptions.DbExceptions;

import java.util.List;

public interface CartDao {
    /**
     * Add cart_has_dish3 to db
     * @param clientId id of client
     * @param dishId id of dish to add
     * @param count count of portions
     */
    void addDishToCart(int clientId, String dishId, int count) throws DbExceptions;

    /**
     * Remove one cart_has_dish3 from user cart
     * @param clientId id of user
     * @param dishId id of dish
     */
    public void removeDishFromCart(int clientId, String dishId) throws DbExceptions;

    /**
     * Make receipt in db and add receipt_has_dish3 for every dish in cart
     * @param clientId id user
     * @param cart list of dishes to add
     */
    public int makeAnOrder(int clientId, List<Dish> cart) throws DbExceptions;

    /**
     * Select all dishes from client Cart
     * @param clientId
     * @return list of dishes
     */
    public List<Dish>dishesFromClientCart(int clientId) throws DbExceptions;
    /**
     * Remove all cart_has_dish of user
     * @param clientId id of user
     */
    public void cleanCart(int clientId)throws DbExceptions;

    /**
     * Get total client cart Price
     * @param dishList
     * @param quantity
     * @return total cart price
     */
    public double getTotalCartPrice(List<Dish>dishList, int quantity);

    /**
     * Select all client dishes from cart_has_dish3
     * @param clientId
     * @return list of client dishes
     */
    public List<Dish>selectDishesByClient(int clientId);

    /**
     * Update count of dishes in cart_has_dish3 after saving
     * @param quantity
     * @param clientId
     */

    public void updateCount(int quantity, int clientId);

    /**
     * Get amount of one dish in the cart_has_dish3
     * @param clientId
     * @param dishId
     * @return count of one dish in client cart
     */

    public int getDishCountInCart (int clientId, String dishId);

    /**
     * Set total order price in order
     * @param user_id
     * @param total
     */
    public void setTotalOrderPrice(int user_id, int total, int orderId);

    /**
     * Check whether this dish is in cart_has_dish3
     * @param dishId
     * @param clientId
     * @return
     */
    public boolean checkWhetherDishExistInClientCart(String dishId, int clientId);

}
