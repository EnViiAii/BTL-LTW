package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.LichSuHanhDong;
import cf.laptrinhweb.btl.model.DieuKienLichSu;

import java.util.List;

public interface LichSuHanhDongRepository extends JdbcRepository {
    void themLichSu(LichSuHanhDong lichSuHanhDong);
    List<LichSuHanhDong> layTheoMaNguoiDung(Long maNguoiDung, DieuKienLichSu dieuKien);
    int demTatCaTheoMaNguoiDung(Long maNguoiDung);
}
