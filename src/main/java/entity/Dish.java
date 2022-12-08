package entity;

public class Dish {

    private String id;

    private String name;

    private long categoryId;

    private long price;

    private long weight;

    private String image;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public long getCategoryId() {
        return categoryId;
    }
    public void setCategory(long category){
        this.categoryId=category;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price){
        this.price=price;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight){
        this.weight=weight;
    }

    public String getImage() {
        return image;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public static class Builder{
        Dish dish=new Dish();

        public Dish getDish(){return dish;}

        public Builder setId(String id){
            dish.id=id;
            return this;
        }

        public Builder setName(String name){
            dish.name=name;
            return this;
        }

        public Builder setCategoryId(long categoryId) {
            dish.categoryId = categoryId;
            return this;
        }

        public Builder setPrice(long price) {
            dish.price = price;
            return this;
        }

        public Builder setWeight(long weight) {
            dish.weight = weight;
            return this;
        }
        public Builder setImage(String image){
            dish.image=image;
            return this;
        }

        public Builder setDescription(String description) {
            dish.description = description;
            return this;
        }

    }



}
