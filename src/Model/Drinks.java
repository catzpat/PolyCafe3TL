package Model;

public class Drinks {

    private String Drink_ID;
    private String Drink_Name;
    private double Price;
    private double Discount;
    private String Img;
    private boolean Drink_Status;
    private String Cate_ID;

    public Drinks(String Drink_ID, String Drink_Name, double Price, double Discount, String Img, boolean Drink_Status, String Cate_ID) {
        this.Drink_ID = Drink_ID;
        this.Drink_Name = Drink_Name;
        this.Price = Price;
        this.Discount = Discount;
        this.Img = Img;
        this.Drink_Status = Drink_Status;
        this.Cate_ID = Cate_ID;
    }

    public String getDrink_ID() {
        return Drink_ID;
    }

    public void setDrink_ID(String Drink_ID) {
        this.Drink_ID = Drink_ID;
    }

    public String getDrink_Name() {
        return Drink_Name;
    }

    public void setDrink_Name(String Drink_Name) {
        this.Drink_Name = Drink_Name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public boolean isDrink_Status() {
        return Drink_Status;
    }

    public void setDrink_Status(boolean Drink_Status) {
        this.Drink_Status = Drink_Status;
    }

    public String getCate_ID() {
        return Cate_ID;
    }

    public void setCate_ID(String Cate_ID) {
        this.Cate_ID = Cate_ID;
    }

    @Override
    public String toString() {
        return "Drinks{" + "Drink_ID=" + Drink_ID + ", Drink_Name=" + Drink_Name + ", Price=" + Price + ", Discount=" + Discount + ", Img=" + Img + ", Drink_Status=" + Drink_Status + ", Cate_ID=" + Cate_ID + '}';
    }

}
