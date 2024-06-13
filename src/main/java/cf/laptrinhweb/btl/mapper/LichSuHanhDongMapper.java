package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.constant.LoaiHanhDong;
import cf.laptrinhweb.btl.entity.LichSuHanhDong;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LichSuHanhDongMapper {
    public LichSuHanhDong map(ResultSet resultSet) throws SQLException {
        return LichSuHanhDong.builder()
            .loaiHanhDong(LoaiHanhDong.cuaGiaTri(resultSet.getLong("loai_hanh_dong")))
            .diaChiIP(resultSet.getString("dia_chi_ip"))
            .thoiGian(resultSet.getTimestamp("thoi_gian"))
            .chiTiet(resultSet.getString("chi_tiet"))
            .thanhCong(resultSet.getBoolean("thanh_cong"))
            .build();
    }
}
