package cf.laptrinhweb.btl.service;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;

import java.util.List;

public interface GioHangService {
    void themSanPham(NguoiDung nguoiDung, Long maSanPham, Integer soLuong);
    List<SanPhamTrongGio> layTatCaCuaNguoiDung(NguoiDung nguoiDung);
    void xoaSanPham(Long maGio, Long nguoidung);
    void capNhatSoLuong(Long maMucGioHang, int soLuongMoi);
}
