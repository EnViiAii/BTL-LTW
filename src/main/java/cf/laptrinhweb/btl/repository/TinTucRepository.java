package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.TinTuc;

import java.util.List;

public interface TinTucRepository extends JdbcRepository {
    List<TinTuc> layTatCa();
}
