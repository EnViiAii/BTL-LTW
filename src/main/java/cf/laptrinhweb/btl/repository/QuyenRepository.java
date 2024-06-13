package cf.laptrinhweb.btl.repository;

import cf.laptrinhweb.btl.entity.Quyen;

import java.util.Optional;

public interface QuyenRepository extends JdbcRepository {
    Optional<Quyen> timBangTen(String tenQuyen);
}
