package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.Quyen;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuyenMapper {
    public Quyen map(ResultSet resultSet) throws SQLException {
        Quyen quyen = new Quyen();
        quyen.setMaQuyen(resultSet.getLong("ma_quyen"));
        quyen.setTenQuyen(resultSet.getString("ten_quyen"));
        quyen.setMoTa(resultSet.getString("mo_ta"));
        return quyen;
    }
}
