package Model;

public class User {

    private String Acc_Name;
    private String Acc_PW;
    private boolean Acc_Status;
    private String FullName;
    private String Acc_Photo;
    private boolean Manager;

    public User(String Acc_Name, String Acc_PW, boolean Acc_Status, String FullName, String Acc_Photo, boolean Manager) {
        this.Acc_Name = Acc_Name;
        this.Acc_PW = Acc_PW;
        this.Acc_Status = Acc_Status;
        this.FullName = FullName;
        this.Acc_Photo = Acc_Photo;
        this.Manager = Manager;
    }

    public String getAcc_Name() {
        return Acc_Name;
    }

    public void setAcc_Name(String Acc_Name) {
        this.Acc_Name = Acc_Name;
    }

    public String getAcc_PW() {
        return Acc_PW;
    }

    public void setAcc_PW(String Acc_PW) {
        this.Acc_PW = Acc_PW;
    }

    public boolean isAcc_Status() {
        return Acc_Status;
    }

    public void setAcc_Status(boolean Acc_Status) {
        this.Acc_Status = Acc_Status;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getAcc_Photo() {
        return Acc_Photo;
    }

    public void setAcc_Photo(String Acc_Photo) {
        this.Acc_Photo = Acc_Photo;
    }

    public boolean isManager() {
        return Manager;
    }

    public void setManager(boolean Manager) {
        this.Manager = Manager;
    }

    @Override
    public String toString() {
        return "User{" + "Acc_Name=" + Acc_Name + ", Acc_PW=" + Acc_PW + ", Acc_Status=" + Acc_Status + ", FullName=" + FullName + ", Acc_Photo=" + Acc_Photo + ", Manager=" + Manager + '}';
    }

    public boolean Acc_Status() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
