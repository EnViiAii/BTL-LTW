package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.TheLoai;
import cf.laptrinhweb.btl.entity.ThuongHieu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThuongHieuMapper {
    public ThuongHieu map(ResultSet resultSet) throws SQLException {
        return ThuongHieu.builder()
            .maThuongHieu(resultSet.getLong("ma_thuong_hieu"))
            .tenThuongHieu(resultSet.getString("ten_thuong_hieu"))
            .build();
    }
}
