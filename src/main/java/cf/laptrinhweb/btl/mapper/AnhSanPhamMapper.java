package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.AnhSanPham;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnhSanPhamMapper {
    public AnhSanPham map(ResultSet resultSet) throws SQLException {
        return AnhSanPham.builder()
            .duongDan(resultSet.getString("duong_dan"))
            .build();
    }
}
