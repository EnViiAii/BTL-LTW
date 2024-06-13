package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;

import java.util.List;

public interface GioHangRepository extends JdbcRepository {
    void themVaoGioHang(Long maNguoiDung, Long maSanPham, Integer soLuong);
    Long timBangNguoiDungVaSanPham(Long maNguoiDung, Long maSanPham);
    void capNhatSoLuong(Long maMucGioHang, Integer soLuongMoi);
    List<SanPhamTrongGio> timTatCaBangMaNguoiDung(Long maNguoiDung);
    void xoaGioHang(Long maSanPhamTrongGio, Long nguoidung);
}
