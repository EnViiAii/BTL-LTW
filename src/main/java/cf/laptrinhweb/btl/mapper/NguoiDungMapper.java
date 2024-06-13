package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.NguoiDung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class NguoiDungMapper {
    public NguoiDung map(ResultSet resultSet) throws SQLException {
        NguoiDung nguoiDung = NguoiDung.builder().build();
        nguoiDung.setMaNguoiDung(resultSet.getLong("ma_nguoi_dung"));
        nguoiDung.setTenHienThi(resultSet.getString("ten_hien_thi"));
        nguoiDung.setTenDangNhap(resultSet.getString("ten_dang_nhap"));
        nguoiDung.setEmail(resultSet.getString("email"));
        nguoiDung.setSoDienThoai(resultSet.getString("so_dien_thoai"));
        nguoiDung.setMatKhau(resultSet.getString("mat_khau"));
        nguoiDung.setDaKhoa(resultSet.getBoolean("da_khoa"));
        nguoiDung.setCoGangDangNhap(resultSet.getInt("co_gang_dang_nhap"));
        nguoiDung.setThoiGianTao(resultSet.getTimestamp("thoi_gian_tao"));
        return nguoiDung;
    }
}
