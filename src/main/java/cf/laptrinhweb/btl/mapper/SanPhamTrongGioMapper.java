package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamTrongGioMapper {
    public SanPhamTrongGio map(ResultSet resultSet) throws SQLException {
        SanPhamMapper sanPhamMapper = new SanPhamMapper();
        SanPham sanPham = sanPhamMapper.mapDonGian(resultSet);
        return SanPhamTrongGio.builder()
            .maMucGioHang(resultSet.getLong("ma_muc_gio_hang"))
            .sanPham(sanPham)
            .soLuong(resultSet.getInt("so_luong_mua"))
            .build();
    }
}
