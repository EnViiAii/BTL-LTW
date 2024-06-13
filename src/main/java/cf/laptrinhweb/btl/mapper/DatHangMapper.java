package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.constant.HinhThucThanhToan;
import cf.laptrinhweb.btl.constant.PhuongThucVanChuyen;
import cf.laptrinhweb.btl.constant.TrangThaiDon;
import cf.laptrinhweb.btl.entity.DatHang;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatHangMapper {
    public DatHang map(ResultSet resultSet) throws SQLException {
        return DatHang.builder()
            .maDatHang(resultSet.getLong("ma_dat_hang"))
            .tenNguoiNhan(resultSet.getString("ten_nguoi_nhan"))
            .diaChiGiao(resultSet.getString("dia_chi_giao"))
            .sdtNhan(resultSet.getString("sdt_nhan"))
            .tongTien(resultSet.getDouble("tong_tien"))
            .ngayTaoDon(resultSet.getTimestamp("ngay_dat_hang").toLocalDateTime())
            .tinhTrang(TrangThaiDon.cuaGiaTri(resultSet.getInt("trang_thai")))
            .hinhThucThanhToan(HinhThucThanhToan.cuaGiaTri(resultSet.getInt("hinh_thuc_thanh_toan")))
            .phuongThucVanChuyen(resultSet.getInt("phuong_thuc_van_chuyen"))
            .ghiChu(resultSet.getString("ghi_chu"))
            .build();
    }
}
