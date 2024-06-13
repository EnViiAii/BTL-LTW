package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.LichSuHanhDong;
import cf.laptrinhweb.btl.mapper.LichSuHanhDongMapper;
import cf.laptrinhweb.btl.model.DieuKienLichSu;
import cf.laptrinhweb.btl.repository.LichSuHanhDongRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LichSuHanhDongRepositoryImpl implements LichSuHanhDongRepository {
    @Override
    public void themLichSu(LichSuHanhDong lichSuHanhDong) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO lich_su_hanh_dong (
                    ma_nguoi_dung,
                    loai_hanh_dong,
                    dia_chi_ip,
                    thoi_gian,
                    chi_tiet,
                    thanh_cong
                ) VALUES (?, ?, ?, ?, ?, ?)
            """);
            ps.setLong(1, lichSuHanhDong.getNguoiDung().getMaNguoiDung());
            ps.setLong(2, lichSuHanhDong.getLoaiHanhDong().getGiaTriNguyen());
            ps.setString(3, lichSuHanhDong.getDiaChiIP());
            ps.setTimestamp(4, Timestamp.from(lichSuHanhDong.getThoiGian().toInstant()));
            ps.setString(5, lichSuHanhDong.getChiTiet());
            ps.setBoolean(6, lichSuHanhDong.isThanhCong());
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Khong the them lich su hanh dong", e);
        }
    }

    @Override
    public List<LichSuHanhDong> layTheoMaNguoiDung(Long maNguoiDung, DieuKienLichSu dieuKien) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM lich_su_hanh_dong
                WHERE ma_nguoi_dung = ?
                ORDER BY thoi_gian DESC
                LIMIT ?, ?
            """);
            ps.setLong(1, maNguoiDung);
            ps.setInt(2, dieuKien.getTrang() * dieuKien.getKichThuoc());
            ps.setInt(3, dieuKien.getKichThuoc());
            ResultSet resultSet = ps.executeQuery();
            List<LichSuHanhDong> danhSachLichSu = new ArrayList<>();
            LichSuHanhDongMapper mapper = new LichSuHanhDongMapper();
            while (resultSet.next()) {
                danhSachLichSu.add(mapper.map(resultSet));
            }
            return danhSachLichSu;
        } catch (Exception e) {
            throw new RuntimeException("Khong the them lich su hanh dong", e);
        }
    }

    @Override
    public int demTatCaTheoMaNguoiDung(Long maNguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT COUNT(*) AS tong_so
                FROM lich_su_hanh_dong
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, maNguoiDung);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) throw new RuntimeException();
            return resultSet.getInt("tong_so");
        } catch (Exception e) {
            throw new RuntimeException("Khong the them lich su hanh dong", e);
        }
    }
}
