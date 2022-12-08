package util;

import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

public class SqlUtils {

    public static final String LOG_IN="select * from client where email=? and password=?";

    public static final String SAVE="insert into client (surname,name,email,phone,password,status) values (?,?,?,?,?,?)";

    public static final String CHANGE_PASSWORD="update client set password = ? where email = ?";
    public static final String GET_ALL_DISHES = "SELECT * FROM dish";
    public static final String GET_SORTED_DISHES_FROM_CATEGORY = "select * from dish order ";
    public static final String ADD_DISH_TO_CART="INSERT INTO cart_has_dish3 (client_id, dish_id, count) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE count = ?";

    public static final String REMOVE_DISH_FROM_CART = "DELETE FROM cart_has_dish3 WHERE client_id = ? AND dish_id = ?";

    public static final String ADD_RECEIPT = "INSERT INTO receipt (user_id) VALUES (?)";

    public static final String ADD_RECEIPT_HAS_DISH = "INSERT INTO receipt_has_dish (receipt_id, dish_id, count, price) VALUES (?, ?, ?, ?)";

    public static final String GET_DISHES_AND_THEIR_COUNT_FROM_CLIENT_CART="select dish_id,count from cart_has_dish3 where client_id=?";

    public static final String CLEAN_CART = "DELETE FROM cart_has_dish3 WHERE client_id = ?";

    public static final String SELECT_ALL_CLIENT_DISHES = "select dish_id from cart_has_dish3 where client_id=?";

    public static final String SELECT_DISHES_FROM_CLIENT_CART = "select dish_id from cart_has_dish3 where client_id=?";

    public static final String GET_ALL_DISHES_FROM_ORDER="select dish_id, count from receipt_has_dish where receipt_id=?";



    public static final String GET_ALL_CLIENT_ORDERS="select * from receipt where user_id=?";

    public static final String GET_DISHES_BY_ORDER_INDEX="SELECT dish.id, dish.name, rhd.price, rhd.count FROM receipt_has_dish AS rhd INNER JOIN dish ON dish.id = rhd.dish_id WHERE receipt_id = ?";

    public static final String GET_ALL_ORDERS="select * from receipt";

    public static final String GET_ORDER_COUNT = "SELECT COUNT(*) FROM receipt";

    public static final String UPDATE_TOTAL_PRICE_IN_RECEIPT = "UPDATE receipt SET total = ? where user_id=? and id=?";

    public static final String UPDATE_COUNT_IN_THE_CART = "UPDATE cart_has_dish3 SET count = ? where client_id=?";

    public static final String GET_COUNT_DISH_IN_THE_CART = "select count from cart_has_dish3 where client_id=? and dish_id=?";

    public static final String GET_RECEIPTS_APPROVED_BY = "SELECT * FROM receipt WHERE manager_id = ?";

    public static final String GET_NOT_APPROVED = "SELECT * FROM receipt WHERE manager_id IS NULL";

    public static final String CHANGE_STATUS="UPDATE receipt SET status_id = ?, manager_id=? WHERE id = ?";





    public static final String GET_ALL_CATEGORIES = "SELECT * FROM category";

    public static final String GET_DISH_BY_ID = "SELECT * FROM dish WHERE id = ?";


    public static final Map<String, String> sortingTypes = new HashMap<>();

    static {
        sortingTypes.put("Price", "price");
        sortingTypes.put("Name", "name");
        sortingTypes.put("Category", "category_id");
    }

    /**
     * rollback connection and log error if it appears
     *
     * @param con Connection to rollback
     */
    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            LogManager.getLogger(SqlUtils.class.getName()).error(e);
        }
    }

    /**
     * rollback connection with savepoint and log error if it appears
     *
     * @param con Connection to rollback
     * @param s   Savepoint to use
     */
    public static void rollback(Connection con, Savepoint s) {
        try {
            con.rollback(s);
        } catch (SQLException e) {
            LogManager.getLogger(SqlUtils.class.getName()).error(e);
        }
    }

    /**
     * close AutoCloseable object and log error if it appears
     *
     * @param closeable object to close
     */
    public static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            LogManager.getLogger(SqlUtils.class.getName()).error(e);
        }
    }
}
