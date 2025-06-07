package Model;

public class Products {

    String MaSP;
    String TenSP;
    String LoaiSP;
    int Gia;
    String HinhAnh;

    public Products(String MaSP, String TenSP, String LoaiSP, int Gia, String HinhAnh) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.LoaiSP = LoaiSP;
        this.Gia = Gia;
        this.HinhAnh = HinhAnh;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    @Override
    public String toString() {
        return "Products{" + "MaSP=" + MaSP + ", TenSP=" + TenSP + ", LoaiSP=" + LoaiSP + ", Gia=" + Gia + ", HinhAnh=" + HinhAnh + '}';
    }

}
