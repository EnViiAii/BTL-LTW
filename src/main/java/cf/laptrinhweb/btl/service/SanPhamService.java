package cf.laptrinhweb.btl.service;

import cf.laptrinhweb.btl.entity.DanhGia;
import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.model.DieuKienSanPham;
import cf.laptrinhweb.btl.model.ThongTinSanPham;

import javax.servlet.http.Part;
import java.util.List;

public interface SanPhamService {
    List<SanPham> timTatCa(DieuKienSanPham dieuKien);
    void luuSanPham(ThongTinSanPham thongTinSanPham, List<Part> dsAnh);
    SanPham timTheoMa(Long maSanPham);
    void giamSoLuong(Long maSanPham, int soLuongGiam);
}
