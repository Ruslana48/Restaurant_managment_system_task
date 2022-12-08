package mysql;

import dao.CartDao;
import dao.DAO;
import entity.Client;
import entity.Dish;
import exceptions.DbExceptions;
import util.SqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoSql implements CartDao {

    @Override
    public void addDishToCart(int clientId, String dishId, int count) throws DbExceptions {
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.ADD_DISH_TO_CART);
            int k = 0;
            ps.setInt(++k, clientId);
            ps.setString(++k, dishId);
            ps.setInt(++k, count);
            ps.setInt(++k, count);
            int a = ps.executeUpdate();
            if (a == 0) {
                throw new DbExceptions("We cant add to cart");
            }

        } catch (SQLException e) {
            throw new DbExceptions("something bad");
        }
    }

    @Override
    public List<Dish> selectDishesByClient(int clientId) {
        List<Dish> clientDishes = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            String query = SqlUtils.GET_DISHES_AND_THEIR_COUNT_FROM_CLIENT_CART;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Dish dish = DAO.getDao().getDishDao().getDishById(Integer.parseInt(rs.getString("dish_id")));
                    clientDishes.add(dish);
                }
            } catch (DbExceptions e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientDishes;
    }

    @Override
    public boolean checkWhetherDishExistInClientCart(String dishId, int clientId) {
        boolean exist=false;
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps= con.prepareStatement(SqlUtils.SELECT_ALL_CLIENT_DISHES);
            ps.setInt(1, clientId);
            List<String>dishesId=new ArrayList<>();
            try(ResultSet rs= ps.executeQuery()){
                while (rs.next()){
                    String dish = rs.getString("dish_id");
                    dishesId.add(dish);
                }
            }
            exist=dishesId.contains(dishId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exist;
    }

    @Override
    public double getTotalCartPrice(List<Dish> dishList, int quantity) {
        double sum = 0;
        String connectionUrl = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
        try (Connection con = DriverManager.getConnection(connectionUrl, "root", "xhz2woVLxhz2woVL");) {
            if (dishList.size() > 0) {
                for (Dish item : dishList) {
                    String query = "select price from dish where id=?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, Integer.parseInt(item.getId()));
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            sum += rs.getDouble("price") * quantity;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }

    public void cleanCart(int clientId) throws DbExceptions {
        String connectionUrl = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.CLEAN_CART);
            ps.setInt(1, clientId);
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Cleaning cart failed, no rows were deleted");
            }
            //con.commit();
        } catch (SQLException e) {
            printSQLException(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeDishFromCart(int clientId, String dishId) throws DbExceptions {
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(SqlUtils.REMOVE_DISH_FROM_CART);
            int k = 0;
            ps.setInt(++k, clientId);
            ps.setString(++k, dishId);
            ps.executeUpdate();
            con.commit();
        } catch (Exception e) {
            //throw new DbExceptions("Cannot remove dish from cart", e);
            e.getMessage();
        }
    }

    @Override
    public List<Dish> dishesFromClientCart(int clientId) throws DbExceptions {
        List<Dish> clientCartDishes = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.SELECT_DISHES_FROM_CLIENT_CART);
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dishId = rs.getString("dish_id");
                Dish dish = DAO.getDao().getDishDao().getDishById(Integer.parseInt(dishId));
                clientCartDishes.add(dish);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientCartDishes;
    }

    @Override
    public int makeAnOrder(int clientId, List<Dish> cart) throws DbExceptions {
        Savepoint savepoint = null;
        int orderId=0;
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            savepoint = con.setSavepoint();;

            orderId = addReceipt(con, clientId);
            int count = 0;
            for (Dish d : cart) {
                count = getDishCountInCart(clientId, d.getId());
                addReceiptHasDish(orderId, d, count);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orderId;
    }

    @Override
    public void setTotalOrderPrice(int user_id, int total, int orderId) {
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.UPDATE_TOTAL_PRICE_IN_RECEIPT);
            int k = 0;
            ps.setInt(++k, total);
            ps.setInt(++k, user_id);
            ps.setInt(++k, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw new RuntimeException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    //////////////////////////////////
    private int addReceipt(Connection con, int user_id) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SqlUtils.ADD_RECEIPT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, user_id);
            int a = ps.executeUpdate();
            if (a == 0) {
                throw new SQLException("Adding failed");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    private void addReceiptHasDish(int orderId, Dish dish, int count) throws SQLException {
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.ADD_RECEIPT_HAS_DISH);;
            int k = 0;
            ps.setInt(++k, orderId);
            ps.setInt(++k, Integer.parseInt(dish.getId()));
            ps.setInt(++k, count);
            ps.setInt(++k, (int) dish.getPrice());
            if (ps.executeUpdate() == 0) {
                throw new SQLException("adding into receipt_has+dish_failed");
            }
        }
    }

    @Override
    public void updateCount(int quantity, int clientId) {
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.UPDATE_COUNT_IN_THE_CART);
            int k = 0;
            ps.setInt(++k, quantity);
            ps.setInt(++k, clientId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getDishCountInCart(int clientId, String dishId) {
        int dishCount = 0;
        String connectionUrl = "jdbc:mysql://localhost:3306/restaurant?serverTimezone=UTC";
        try (Connection con = DriverManager.getConnection(connectionUrl, "root", "xhz2woVLxhz2woVL");) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.GET_COUNT_DISH_IN_THE_CART);
            int k = 0;
            ps.setInt(++k, clientId);
            ps.setString(++k, dishId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dishCount = rs.getInt("count");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dishCount;
    }


}

