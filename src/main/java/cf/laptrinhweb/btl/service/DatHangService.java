package cf.laptrinhweb.btl.service;
import cf.laptrinhweb.btl.entity.*;
import java.util.List;

public interface DatHangService {
    void themDatHang(DatHang dathang, List<SanPhamTrongGio> listGio, NguoiDung nguoidung);
    List<DatHang> layTatCaCuaNguoiDung(NguoiDung nguoidung);
    DatHang layDatHang(Long ma_dat_hang);
    DatHang layDonTheoMaDatHang(Long maDatHang, NguoiDung nguoidung);
    void thayDoiTrangThaiDon(Long maDatHang, int trang_thai_moi);
}
