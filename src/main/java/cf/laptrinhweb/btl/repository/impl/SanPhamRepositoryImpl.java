package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.DanhGia;
import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.mapper.SanPhamMapper;
import cf.laptrinhweb.btl.model.DieuKienSanPham;
import cf.laptrinhweb.btl.model.ThongTinSanPham;
import cf.laptrinhweb.btl.repository.SanPhamRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SanPhamRepositoryImpl implements SanPhamRepository {
    @Override
    public SanPham taoMoi(ThongTinSanPham thongTinSanPham) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO san_pham (
                    ten_san_pham,
                    anh_xem_truoc,
                    mo_ta,
                    gia,
                    so_luong,
                    kich_thuoc,
                    trong_luong,
                    ma_the_loai,
                    ma_chat_lieu,
                    ma_thuong_hieu,
                    da_an)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, thongTinSanPham.getTen());
            ps.setString(2, thongTinSanPham.getAnhXemTruoc());
            ps.setString(3, thongTinSanPham.getMoTa());
            ps.setDouble(4, thongTinSanPham.getGia());
            ps.setInt(5, thongTinSanPham.getSoLuong());
            ps.setString(6, thongTinSanPham.getKichThuoc());
            ps.setObject(7, thongTinSanPham.getTrongLuong()); // dung setObject de tranh truong hop loi khi trong luong null
            ps.setLong(8, thongTinSanPham.getMaTheLoai());
            ps.setLong(9, thongTinSanPham.getMaChatLieu());
            ps.setLong(10, thongTinSanPham.getMaThuongHieu());
            ps.setBoolean(11, thongTinSanPham.isDaAn());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            resultSet.next();
            return SanPham.builder().maSanPham(resultSet.getLong(1)).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<SanPham> timTheoMa(Long maSanPham) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM san_pham
                LEFT JOIN the_loai tl
                    ON san_pham.ma_the_loai = tl.ma_the_loai
                LEFT JOIN thuong_hieu th
                    ON san_pham.ma_thuong_hieu = th.ma_thuong_hieu
                LEFT JOIN chat_lieu cl
                    ON san_pham.ma_chat_lieu = cl.ma_chat_lieu
                WHERE ma_san_pham = ?
            """);
            ps.setLong(1, maSanPham);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next() ? Optional.of(new SanPhamMapper().map(resultSet)) : Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Khong the tim san pham theo ma", e);
        }
    }

    @Override
    public List<SanPham> timTatCa(DieuKienSanPham dieuKien) {
        try (Connection ketNoi = moKetNoi()) {
            String truyVan = """
                SELECT *
                FROM san_pham
                LEFT JOIN the_loai tl
                    ON san_pham.ma_the_loai = tl.ma_the_loai
                LEFT JOIN thuong_hieu th
                    ON san_pham.ma_thuong_hieu = th.ma_thuong_hieu
                LEFT JOIN chat_lieu cl
                    ON san_pham.ma_chat_lieu = cl.ma_chat_lieu
                WHERE
                    (? IS NULL OR da_an = ?)
                    AND (? IS NULL OR ten_san_pham LIKE ?)
                ORDER BY ma_san_pham DESC
                LIMIT ?, ?
            """;
            PreparedStatement ps = ketNoi.prepareStatement(truyVan);
            ps.setObject(1, dieuKien.getDaAn());
            ps.setObject(2, dieuKien.getDaAn());
            ps.setObject(3, dieuKien.getTuKhoa());
            ps.setObject(4, "%" + dieuKien.getTuKhoa() + "%");
            ps.setInt(5, dieuKien.getTrang() * dieuKien.getKichThuoc());
            ps.setInt(6, dieuKien.getKichThuoc());
            ResultSet resultSet = ps.executeQuery();
            List<SanPham> danhSachSanPham = new ArrayList<>();
            SanPhamMapper mapper = new SanPhamMapper();
            while (resultSet.next()) {
                danhSachSanPham.add(mapper.map(resultSet));
            }
            return danhSachSanPham;
        } catch (Exception e) {
            throw new RuntimeException("Khong the tim san pham theo ma", e);
        }
    }

    public void giamSoLuong(Long maSanPham, int soLuongGiam) {
      try (Connection ketNoi = moKetNoi()) {
        PreparedStatement ps = ketNoi.prepareStatement("""
                  UPDATE san_pham
                  SET so_luong = so_luong - ?
                  WHERE ma_san_pham = ?
              """);
        ps.setInt(1, soLuongGiam);
        ps.setLong(2, maSanPham);
        ps.executeUpdate();
      }
      catch(Exception e) {
        throw new RuntimeException("Khong the giam so luong san pham", e);
      }
    }


    @Override
    public void capNhat(ThongTinSanPham thongTinSanPham) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                UPDATE san_pham
                SET ten_san_pham = ?,
                    mo_ta = ?,
                    gia = ?,
                    so_luong = ?,
                    kich_thuoc = ?,
                    trong_luong = ?,
                    ma_the_loai = ?,
                    ma_chat_lieu = ?,
                    ma_thuong_hieu = ?,
                    da_an = ?
                WHERE ma_san_pham = ?
            """);
            ps.setString(1, thongTinSanPham.getTen());
            ps.setString(2, thongTinSanPham.getMoTa());
            ps.setDouble(3, thongTinSanPham.getGia());
            ps.setInt(4, thongTinSanPham.getSoLuong());
            ps.setString(5, thongTinSanPham.getKichThuoc());
            ps.setObject(6, thongTinSanPham.getTrongLuong()); // dung setObject de tranh truong hop loi khi trong luong null
            ps.setLong(7, thongTinSanPham.getMaTheLoai());
            ps.setLong(8, thongTinSanPham.getMaChatLieu());
            ps.setLong(9, thongTinSanPham.getMaThuongHieu());
            ps.setBoolean(10, thongTinSanPham.isDaAn());
            ps.setLong(11, thongTinSanPham.getMaSanPham());
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Khong the cap nhat thong tin san pham", e);
        }
    }
}
