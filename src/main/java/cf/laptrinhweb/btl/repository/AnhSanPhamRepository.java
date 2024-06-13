package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.AnhSanPham;
import cf.laptrinhweb.btl.entity.SanPham;

import java.util.List;

public interface AnhSanPhamRepository extends JdbcRepository {
    void themTatCaAnh(SanPham sanPham, List<String> cacDuongDan);
    List<AnhSanPham> timTheoMaSanPham(Long maSanPham);
}
