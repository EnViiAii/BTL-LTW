package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.TheLoai;

import java.util.List;

public interface TheLoaiRepository extends JdbcRepository {
    List<TheLoai> layTatCa();
    TheLoai timTheLoai(Long ma_the_loai);
}
