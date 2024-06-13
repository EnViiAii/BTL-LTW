package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamTrongGio;
import cf.laptrinhweb.btl.mapper.SanPhamTrongGioMapper;
import cf.laptrinhweb.btl.repository.GioHangRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GioHangRepositoryImpl implements GioHangRepository {
    @Override
    public void themVaoGioHang(Long maNguoiDung, Long maSanPham, Integer soLuong) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO gio_hang (
                    ma_nguoi_dung,
                    ma_san_pham,
                    so_luong_mua
                ) VALUES (?, ?, ?)
            """);
            ps.setLong(1, maNguoiDung);
            ps.setLong(2, maSanPham);
            ps.setInt(3, soLuong);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Khong the them sp vao gio hang", e);
        }
    }

    @Override
    public Long timBangNguoiDungVaSanPham(Long maNguoiDung, Long maSanPham) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT ma_muc_gio_hang
                FROM gio_hang
                WHERE ma_nguoi_dung = ? AND ma_san_pham = ?
            """);
            ps.setLong(1, maNguoiDung);
            ps.setLong(2, maSanPham);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                return null;
            } else {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            throw new RuntimeException("Khong the truy van gio hang", e);
        }
    }

    @Override
    public void capNhatSoLuong(Long maMucGioHang, Integer soLuongMoi) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                UPDATE gio_hang
                SET so_luong_mua = ?
                WHERE ma_muc_gio_hang = ?
            """);
            ps.setLong(1, soLuongMoi);
            ps.setLong(2, maMucGioHang);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Khong the truy van gio hang", e);
        }
    }

    @Override
    public List<SanPhamTrongGio> timTatCaBangMaNguoiDung(Long maNguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM gio_hang
                JOIN san_pham
                    ON gio_hang.ma_san_pham = san_pham.ma_san_pham
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, maNguoiDung);
            ResultSet resultSet = ps.executeQuery();
            List<SanPhamTrongGio> dsSanPham = new ArrayList<>();
            SanPhamTrongGioMapper mapper = new SanPhamTrongGioMapper();
            while (resultSet.next()) {
                dsSanPham.add(mapper.map(resultSet));
            }
            return dsSanPham;
        } catch (Exception e) {
            throw new RuntimeException("Khong the truy van gio hang", e);
        }
    }

	@Override
	public void xoaGioHang(Long maSanPhamTrongGio, Long nguoidung) {
		// TODO Auto-generated method stub
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                DELETE 
                FROM gio_hang
                WHERE ma_muc_gio_hang = ? AND ma_nguoi_dung = ?
            """);
            ps.setLong(1, maSanPhamTrongGio);
            ps.setLong(2,  nguoidung);     
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Khong the xoa san pham khoi gio hang", e);
        }
		
	}


}
