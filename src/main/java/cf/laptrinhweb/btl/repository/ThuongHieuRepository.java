package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.TheLoai;
import cf.laptrinhweb.btl.entity.ThuongHieu;

import java.util.List;

public interface ThuongHieuRepository extends JdbcRepository {
    List<ThuongHieu> layTatCa();
    ThuongHieu timThuongHieu(Long ma_thuong_hieu);
}
