package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.AnhSanPham;
import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.mapper.AnhSanPhamMapper;
import cf.laptrinhweb.btl.repository.AnhSanPhamRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnhSanPhamRepositoryImpl implements AnhSanPhamRepository {
    @Override
    public void themTatCaAnh(SanPham sanPham, List<String> cacDuongDan) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO anh_san_pham (ma_san_pham, duong_dan)
                VALUES (?, ?)
            """);
            for (String duongDanAnh : cacDuongDan) {
                ps.setLong(1, sanPham.getMaSanPham());
                ps.setString(2, duongDanAnh);
                // them mot bo tham so moi cho VALUES
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            throw new RuntimeException("Khong the luu cac anh vao csdl", e);
        }
    }

    @Override
    public List<AnhSanPham> timTheoMaSanPham(Long maSanPham) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT duong_dan
                FROM anh_san_pham
                WHERE ma_san_pham = ?
            """);
            ps.setLong(1, maSanPham);
            ResultSet resultSet = ps.executeQuery();
            List<AnhSanPham> dsAnh = new ArrayList<>();
            AnhSanPhamMapper mapper = new AnhSanPhamMapper();
            while (resultSet.next()) {
                dsAnh.add(mapper.map(resultSet));
            }
            return dsAnh;
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay danh sach anh", e);
        }
    }
}
