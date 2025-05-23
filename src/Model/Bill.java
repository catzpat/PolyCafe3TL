package Model;

import java.util.Date;

public class Bill {

    private long Bill_ID;
    private String Acc_Name;
    private int Cards_ID;
    private Date Checkin = new Date();
    private Date Checkout;
    private int Bill_Status;

    public Bill(long Bill_ID, String Acc_Name, int Cards_ID, Date Checkout, int Bill_Status) {
        this.Bill_ID = Bill_ID;
        this.Acc_Name = Acc_Name;
        this.Cards_ID = Cards_ID;
        this.Checkout = Checkout;
        this.Bill_Status = Bill_Status;
    }

    public long getBill_ID() {
        return Bill_ID;
    }

    public void setBill_ID(long Bill_ID) {
        this.Bill_ID = Bill_ID;
    }

    public String getAcc_Name() {
        return Acc_Name;
    }

    public void setAcc_Name(String Acc_Name) {
        this.Acc_Name = Acc_Name;
    }

    public int getCards_ID() {
        return Cards_ID;
    }

    public void setCards_ID(int Cards_ID) {
        this.Cards_ID = Cards_ID;
    }

    public Date getCheckin() {
        return Checkin;
    }

    public void setCheckin(Date Checkin) {
        this.Checkin = Checkin;
    }

    public Date getCheckout() {
        return Checkout;
    }

    public void setCheckout(Date Checkout) {
        this.Checkout = Checkout;
    }

    public int getBill_Status() {
        return Bill_Status;
    }

    public void setBill_Status(int Bill_Status) {
        this.Bill_Status = Bill_Status;
    }

    @Override
    public String toString() {
        return "Bill{" + "Bill_ID=" + Bill_ID + ", Acc_Name=" + Acc_Name + ", Cards_ID=" + Cards_ID + ", Checkin=" + Checkin + ", Checkout=" + Checkout + ", Bill_Status=" + Bill_Status + '}';
    }

}
