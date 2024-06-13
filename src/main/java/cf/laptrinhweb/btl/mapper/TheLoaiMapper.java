package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.TheLoai;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TheLoaiMapper {
    public TheLoai map(ResultSet resultSet) throws SQLException {
        return TheLoai.builder()
            .maTheLoai(resultSet.getLong("ma_the_loai"))
            .tenTheLoai(resultSet.getString("ten_the_loai"))
            .anhDaiDien(resultSet.getString("anh_dai_dien"))
            .build();
    }
}
