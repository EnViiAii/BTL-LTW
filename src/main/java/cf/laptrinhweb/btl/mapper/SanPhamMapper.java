package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.entity.TheLoai;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamMapper {
    public SanPham map(ResultSet resultSet) throws SQLException {
        SanPham sanPham = mapDonGian(resultSet);
        sanPham.setTheLoai(new TheLoaiMapper().map(resultSet));
        sanPham.setThuongHieu(new ThuongHieuMapper().map(resultSet));
        sanPham.setChatLieu(new ChatLieuMapper().map(resultSet));
        return sanPham;
    }

    public SanPham mapDonGian(ResultSet resultSet) throws SQLException {
        return SanPham.builder()
            .maSanPham(resultSet.getLong("ma_san_pham"))
            .tenSanPham(resultSet.getString("ten_san_pham"))
            .anhXemTruoc(resultSet.getString("anh_xem_truoc"))
            .diemTrungBinh(resultSet.getDouble("diem_trung_binh"))
            .soDanhGia(resultSet.getInt("so_danh_gia"))
            .moTa(resultSet.getString("mo_ta"))
            .gia(resultSet.getDouble("gia"))
            .soLuong(resultSet.getInt("so_luong"))
            .trongLuong((Double) resultSet.getObject("trong_luong"))
            .kichThuoc(resultSet.getString("kich_thuoc"))
            .daAn(resultSet.getBoolean("da_an"))
            .build();
    }
}
