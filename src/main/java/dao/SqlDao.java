package dao;

import mysql.CartDaoSql;
import mysql.CategorySql;
import mysql.DishDaoSql;
import mysql.ReceiptDaoSql;

public class SqlDao extends DAO{
    @Override
    public OrderDao getOrderDao() {
        return new ReceiptDaoSql();
    }

    @Override
    public DishDao getDishDao() {
        return new DishDaoSql();
    }

    @Override
    public CartDao getCartDao() {
        return new CartDaoSql();
    }

    @Override
    public CategoryDAO getCategoryDao() {
        return new CategorySql();
    }

}
