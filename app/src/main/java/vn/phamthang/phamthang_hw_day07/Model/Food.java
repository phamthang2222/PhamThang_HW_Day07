package vn.phamthang.phamthang_hw_day07.Model;

public class Food {
    private int id;
    private int imageFood;
    private String foodName;
    private double price;
    private double weight;

    public Food() {
    }

    public Food(int id, int imageFood, String foodName, double price) {
        this.id = id;
        this.imageFood = imageFood;
        this.foodName = foodName;
        this.price = price;
        this.weight = 1;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getImageFood() {
        return imageFood;
    }

    public void setImageFood(int imageFood) {
        this.imageFood = imageFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", imageFood=" + imageFood +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                '}';
    }
    public void totalCart(){

    }
}
