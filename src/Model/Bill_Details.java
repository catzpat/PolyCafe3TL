package Model;

public class Bill_Details {
    private long BillsDetails_ID; 
    private long Bill_ID; 
    private String Drink_ID; 
    private float Price;
    private float Discount; 
    private int Quantity;
    private String drink_name;

    public Bill_Details(long BillsDetails_ID, long Bill_ID, String Drink_ID, float Price, float Discount, int Quantity, String drink_name) {
        this.BillsDetails_ID = BillsDetails_ID;
        this.Bill_ID = Bill_ID;
        this.Drink_ID = Drink_ID;
        this.Price = Price;
        this.Discount = Discount;
        this.Quantity = Quantity;
        this.drink_name = drink_name;
    }

    public long getBillsDetails_ID() {
        return BillsDetails_ID;
    }

    public void setBillsDetails_ID(long BillsDetails_ID) {
        this.BillsDetails_ID = BillsDetails_ID;
    }

    public long getBill_ID() {
        return Bill_ID;
    }

    public void setBill_ID(long Bill_ID) {
        this.Bill_ID = Bill_ID;
    }

    public String getDrink_ID() {
        return Drink_ID;
    }

    public void setDrink_ID(String Drink_ID) {
        this.Drink_ID = Drink_ID;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public float getDiscount() {
        return Discount;
    }

    public void setDiscount(float Discount) {
        this.Discount = Discount;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getDrink_name() {
        return drink_name;
    }

    public void setDrink_name(String drink_name) {
        this.drink_name = drink_name;
    }

    @Override
    public String toString() {
        return "Bill_Details{" + "BillsDetails_ID=" + BillsDetails_ID + ", Bill_ID=" + Bill_ID + ", Drink_ID=" + Drink_ID + ", Price=" + Price + ", Discount=" + Discount + ", Quantity=" + Quantity + ", drink_name=" + drink_name + '}';
    }
    
    
}
