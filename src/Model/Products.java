package Model;

public class Products {

    String MaSP;
    String TenSP;
    String LoaiSP;
    int Gia;
    String HinhAnh;
    int TrangThai;

    public Products() {
    }

    public Products(String MaSP, String TenSP, String LoaiSP, int Gia, String HinhAnh, int TrangThai) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.LoaiSP = LoaiSP;
        this.Gia = Gia;
        this.HinhAnh = HinhAnh;
        this.TrangThai = TrangThai;
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "Products{" + "MaSP=" + MaSP + ", TenSP=" + TenSP + ", LoaiSP=" + LoaiSP + ", Gia=" + Gia + ", HinhAnh=" + HinhAnh + ", TrangThai=" + TrangThai + '}';
    }

}
