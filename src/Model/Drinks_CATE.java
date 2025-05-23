package Model;

public class Drinks_CATE {
    private String Cate_ID;
    private String Cate_Name;

    public Drinks_CATE(String Cate_ID, String Cate_Name) {
        this.Cate_ID = Cate_ID;
        this.Cate_Name = Cate_Name;
    }

    public String getCate_ID() {
        return Cate_ID;
    }

    public void setCate_ID(String Cate_ID) {
        this.Cate_ID = Cate_ID;
    }

    public String getCate_Name() {
        return Cate_Name;
    }

    public void setCate_Name(String Cate_Name) {
        this.Cate_Name = Cate_Name;
    }

    @Override
    public String toString() {
        return "Drinks_CATE{" + "Cate_ID=" + Cate_ID + ", Cate_Name=" + Cate_Name + '}';
    }
    
}
