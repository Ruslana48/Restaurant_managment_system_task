package dao;

public abstract class DAO {

    public static DAO getDao(){
        return new SqlDao();
    }

    public abstract OrderDao getOrderDao();

    public abstract DishDao getDishDao();

    public abstract CartDao getCartDao();

    public abstract CategoryDAO getCategoryDao();
}
