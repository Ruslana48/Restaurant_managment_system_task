package entity;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private long userId;
    private Status status;
    private long total;
    private long managerId;
    private Date createDate;
    private List<Dish> dishes;

    public int getId() {
        return id;
    }

    public long getClientId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public long getTotal() {
        return total;
    }

    public long getManagerId() {
        return managerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public static class Builder {
        private final Order order = new Order();

        public Builder setId(int id) {
            order.id = id;
            return this;
        }

        public Builder setUserId(long userId) {
            order.userId = userId;
            return this;
        }

        public Builder setStatusId(long statusId) {
            order.status = Status.getStatus(statusId);
            return this;
        }

        public Builder setTotal(long total) {
            order.total = total;
            return this;
        }

        public Builder setManagerId(long managerId) {
            order.managerId = managerId;
            return this;
        }

        public Builder setCreateDate(Date createDate) {
            order.createDate = createDate;
            return this;
        }

        public Builder setDishes(List<Dish> dishes) {
            order.dishes = dishes;
            return this;
        }

        public Order getOrder() {
            return order;
        }
    }

    public static class Dish {
        private long id;
        private String name;
        private int count;
        private int price;

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        public int getPrice() {
            return price;
        }

        public static class Builder {
            private final Dish dish = new Dish();

            public Builder setId(long id) {
                dish.id = id;
                return this;
            }

            public Builder setName(String name) {
                dish.name = name;
                return this;
            }

            public Builder setCount(int count) {
                dish.count = count;
                return this;
            }

            public Builder setPrice(int price) {
                dish.price = price;
                return this;
            }

            public Dish getDish() {
                return dish;
            }

            @Override
            public String toString() {
                return "Dish=" + dish;
            }
        }
    }
}
