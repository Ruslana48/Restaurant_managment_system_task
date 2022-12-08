package mysql;
import dao.DAO;
import dao.OrderDao;
import entity.Dish;
import entity.Order;
import exceptions.DbExceptions;
import util.SqlUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDaoSql implements OrderDao {
    public static Order setOrder(ResultSet rs) throws SQLException{
        return new Order.Builder().setId(rs.getInt("id"))
                .setUserId(rs.getInt("user_id"))
                .setStatusId(rs.getInt("status_id"))
                .setTotal(rs.getLong("total"))
                .setManagerId(rs.getInt("manager_id"))
                .setCreateDate(rs.getTimestamp("create_date"))
                .getOrder();
    }

    @Override
    public void changeStatus(int order_id, int statusId, int managerId) throws DbExceptions {
        try(Connection con =ConnectionPool.getInstance().getConnection();){
            PreparedStatement ps = con.prepareStatement(SqlUtils.CHANGE_STATUS);
            int k = 0;
            ps.setInt(++k, statusId);
            ps.setInt(++k, managerId);
            ps.setInt(++k, order_id);
            if(ps.executeUpdate()==0){
                throw new SQLException("no row attached");
            }
        }catch (SQLException e){
            throw new DbExceptions("cannot change", e);
        }
    }

    @Override
    public List<Order.Dish>getDishesByOrderIndex(int order_id) {
        List<Order.Dish> dishes = new ArrayList<>();
        try(Connection con =ConnectionPool.getInstance().getConnection();){
            PreparedStatement ps = con.prepareStatement(SqlUtils.GET_DISHES_BY_ORDER_INDEX);
            ps.setInt(1, order_id);
            try (ResultSet rs=ps.executeQuery()){
                while (rs.next()){
                    Order.Dish dish=new Order.Dish.Builder()
                            .setId(rs.getInt("id"))
                            .setName(rs.getString("name"))
                            .setPrice(rs.getInt("price"))
                            .setCount(rs.getInt("count"))
                            .getDish();
                    dishes.add(dish);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dishes;
    }

    @Override
    public List<Order> getAllClientOrder(int clientId)throws DbExceptions{
        List<Order>orders=new ArrayList<>();
        try(Connection con =ConnectionPool.getInstance().getConnection();){
            PreparedStatement ps=con.prepareStatement(SqlUtils.GET_ALL_CLIENT_ORDERS);
            ps.setInt(1, clientId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Order order=setOrder(rs);
                order.setDishes(getDishesByOrderIndex((int) order.getId()));
                orders.add(order);
            }
            return orders;
        }
        catch (SQLException ex) {
            throw new DbExceptions("Cannot get all client orders", ex);
        }
    }

    @Override
    public List<Order> getAllOrders() throws DbExceptions {
        List<Order>orders=new ArrayList<>();
        try(Connection con =ConnectionPool.getInstance().getConnection();){
            PreparedStatement ps=con.prepareStatement(SqlUtils.GET_ALL_ORDERS);
            ResultSet rs=ps.executeQuery();
                while (rs.next()){
                    Order order=setOrder(rs);
                    order.setDishes(getDishesByOrderIndex((int) order.getId()));
                    orders.add(order);
                }
                return orders;
            }
        catch (SQLException ex) {
            throw new DbExceptions("Cannot get all client orders", ex);
        }
    }

    @Override
    public List<Dish> getAllOrderDishes(int orderId) throws DbExceptions{
        List<Dish> allDishes=new ArrayList<>();
        try(Connection con =ConnectionPool.getInstance().getConnection();){
            PreparedStatement ps = con.prepareStatement(SqlUtils.GET_ALL_DISHES_FROM_ORDER);
            ps.setInt(1, orderId);
            try(ResultSet rs=ps.executeQuery()) {
                while (rs.next()) {
                    allDishes.add(DAO.getDao().getDishDao().getDishById(rs.getInt("dish_id")));

                }
            }
            return allDishes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAcceptedOrders(int manager_id) throws DbExceptions {
        List<Order>orders=new ArrayList<>();
        try(Connection con =ConnectionPool.getInstance().getConnection();){
            PreparedStatement ps=con.prepareStatement(SqlUtils.GET_RECEIPTS_APPROVED_BY);
            ps.setLong(1, manager_id);
            try(ResultSet rs=ps.executeQuery()) {
                while (rs.next()) {
                    Order order = setOrder(rs);
                    order.setDishes(getDishesByOrderIndex((int) order.getId()));
                    orders.add(order);
                }
            }
            return orders;
        }
        catch (SQLException ex) {
            throw new DbExceptions("Cannot get all orders approved by manager", ex);
        }
    }

//    @Override
//    public List<Order> getNewOrders() throws DbExceptions {
//        List<Order>orders=new ArrayList<>();
//        try(Connection con =ConnectionPool.getInstance().getConnection();){
//            PreparedStatement ps=con.prepareStatement(SqlUtils.GET_NOT_APPROVED);
//            ResultSet rs=ps.executeQuery();
//            while (rs.next()){
//                Order order=setOrder(rs);
//                order.setDishes(getDishesByOrderIndex((int) order.getId()));
//                orders.add(order);
//            }
//            return orders;
//        }
//        catch (SQLException ex) {
//            throw new DbExceptions("Cannot get all new orders", ex);
//
//        }
//    }

    @Override
    public  int getOrderNumber() throws DbExceptions {
        try (Connection con = ConnectionPool.getInstance().getConnection();) {
            PreparedStatement ps = con.prepareStatement(SqlUtils.GET_ORDER_COUNT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new DbExceptions("Cannot getOrderNumber", e);
        }
    }
    }
