package cf.laptrinhweb.btl.service;

import cf.laptrinhweb.btl.constant.LoaiHanhDong;
import cf.laptrinhweb.btl.entity.LichSuHanhDong;
import cf.laptrinhweb.btl.model.DieuKienLichSu;
import cf.laptrinhweb.btl.model.Trang;

import javax.servlet.http.HttpServletRequest;

public interface LichSuHanhDongService {
    void themLichSu(HttpServletRequest request, Long maNguoiDung, LoaiHanhDong loaiHanhDong, boolean thanhCong);
    void themLichSu(HttpServletRequest request, LoaiHanhDong loaiHanhDong, boolean thanhCong);
    Trang<LichSuHanhDong> xemLichSuHanhDong(Long maNguoiDung, DieuKienLichSu dieuKien);
    void themLichSu(HttpServletRequest request, LoaiHanhDong loaiHanhDong, String chiTiet, boolean thanhCong);
}
