package dao;

import entity.Dish;
import entity.Order;
import exceptions.DbExceptions;

import java.util.List;

public interface OrderDao {

    /**
     *Change status of receipt
     * @param order_id
     * @param statusId
     * @param managerId
     */

    void changeStatus(int order_id, int statusId, int managerId) throws DbExceptions;

    //List<Order> getClientOrders(int user_id) throws DbExceptions;

    /**
     * Get all client orders from receipt
     */
    List<Order>getAllOrders() throws DbExceptions;

    /**
     *Get order accepted by Manager
     * @param manager_id
     */

    List<Order>getAcceptedOrders(int manager_id) throws DbExceptions;

    /**
     * Get NEW orders
     */

   // List<Order>getNewOrders() throws DbExceptions;

    /**
     * Get all client orders from receipt
     * @param clientId
     * @return
     * @throws DbExceptions
     */
    public List<Order> getAllClientOrder(int clientId)throws DbExceptions;

    /**
     * How many rows are in dish table
     * @return number of dishes
     */
    int getOrderNumber() throws DbExceptions;

    /**
     * Get List of dishes by order index
     * @param order_id
     * @return list of dishes
     */

    public List<Order.Dish>getDishesByOrderIndex(int order_id);

    /**
     * Get all dishes from one order
     * @param clientId
     * @return List of dishes
     * @throws DbExceptions
     */
    public List<Dish> getAllOrderDishes(int clientId) throws DbExceptions;
}
