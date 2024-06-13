package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.mapper.QuyenMapper;
import cf.laptrinhweb.btl.entity.Quyen;
import cf.laptrinhweb.btl.repository.QuyenRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class QuyenRepositoryImpl implements QuyenRepository {
    @Override
    public Optional<Quyen> timBangTen(String tenQuyen) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT * FROM quyen WHERE ten_quyen = ?
            """);
            ps.setString(1, tenQuyen);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next() ? Optional.of(new QuyenMapper().map(resultSet)) : Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
