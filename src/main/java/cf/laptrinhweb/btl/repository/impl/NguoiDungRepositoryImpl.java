package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.mapper.NguoiDungMapper;
import cf.laptrinhweb.btl.model.DieuKienNguoiDung;
import cf.laptrinhweb.btl.repository.NguoiDungRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NguoiDungRepositoryImpl implements NguoiDungRepository {
    @Override
    public Optional<NguoiDung> timBangThongTinDangNhap(String thongTinDangNhap) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT  *
                FROM    nguoi_dung
                WHERE   ten_dang_nhap = ?
                        OR email = ?
                        OR so_dien_thoai = ?
            """);
            ps.setString(1, thongTinDangNhap);
            ps.setString(2, thongTinDangNhap);
            ps.setString(3, thongTinDangNhap);

            ResultSet resultSet = ps.executeQuery();
            return resultSet.next() ? Optional.of(new NguoiDungMapper().map(resultSet)) : Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tenDangNhapNguoiKhacDaDung(String tenDangNhap, Long maNguoiMuonKiemTra) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT  TRUE
                FROM    nguoi_dung
                WHERE   ten_dang_nhap = ? AND ma_nguoi_dung <> ?
            """);
            ps.setString(1, tenDangNhap);
            ps.setObject(2, maNguoiMuonKiemTra);
            return ps.executeQuery().next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean emailNguoiKhacDaDung(String email, Long maNguoiMuonKiemTra) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT  TRUE
                FROM    nguoi_dung
                WHERE   email = ? AND ma_nguoi_dung <> ?
            """);
            ps.setString(1, email);
            ps.setObject(2, maNguoiMuonKiemTra);
            return ps.executeQuery().next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean soDienThoaiNguoiKhacDaDung(String soDienThoai, Long maNguoiMuonKiemTra) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT  TRUE
                FROM    nguoi_dung
                WHERE   so_dien_thoai = ? AND ma_nguoi_dung <> ?
            """);
            ps.setString(1, soDienThoai);
            ps.setObject(2, maNguoiMuonKiemTra);
            return ps.executeQuery().next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void taoMoiNguoiDung(NguoiDung nguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO nguoi_dung (
                    ten_hien_thi,
                    ten_dang_nhap,
                    email,
                    so_dien_thoai,
                    mat_khau,
                    thoi_gian_tao)
                VALUES (?, ?, ?, ?, ?, ?)
            """, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nguoiDung.getTenHienThi());
            ps.setObject(2, nguoiDung.getTenDangNhap());
            ps.setString(3, nguoiDung.getEmail());
            ps.setString(4, nguoiDung.getSoDienThoai());
            ps.setString(5, nguoiDung.getMatKhau());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            resultSet.next();
            nguoiDung.setMaNguoiDung(resultSet.getLong(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doiMatKhau(NguoiDung nguoiDung, String matKhauMoi) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                UPDATE nguoi_dung
                SET mat_khau = ?
                WHERE ma_nguoi_dung = ?
            """);
            ps.setString(1, matKhauMoi);
            ps.setLong(2, nguoiDung.getMaNguoiDung());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NguoiDung> timTatCa(DieuKienNguoiDung dieuKien) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement truyVan = dieuKien.getQuyen() != null
                ? taoTruyVanVoiQuyen(ketNoi, dieuKien)
                : taoTruyVanKhongQuyen(ketNoi, dieuKien);

            String tuKhoa = dieuKien.getTuKhoa()!=null ? dieuKien.getTuKhoa() + "%" : "%";
            truyVan.setString(1, tuKhoa);
            truyVan.setString(2, tuKhoa);
            truyVan.setString(3, tuKhoa);
            truyVan.setInt(4, dieuKien.getTrang() * dieuKien.getKichThuoc());
            truyVan.setInt(5, dieuKien.getKichThuoc());

            ResultSet resultSet = truyVan.executeQuery();
            NguoiDungMapper mapper = new NguoiDungMapper();
            List<NguoiDung> dsNguoiDung = new ArrayList<>();
            while (resultSet.next()) {
                dsNguoiDung.add(mapper.map(resultSet));
            }
            return dsNguoiDung;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement taoTruyVanKhongQuyen(Connection ketNoi, DieuKienNguoiDung dieuKien) throws SQLException {
        return ketNoi.prepareStatement("""
                SELECT *
                FROM nguoi_dung
                WHERE
                    ten_dang_nhap LIKE ?
                    OR email LIKE ?
                    OR so_dien_thoai LIKE ?
                LIMIT ?, ?
            """);
    }

    private PreparedStatement taoTruyVanVoiQuyen(Connection ketNoi, DieuKienNguoiDung dieuKien) throws SQLException {
        return ketNoi.prepareStatement(String.format("""
            SELECT nguoi_dung.*
            FROM nguoi_dung
            LEFT JOIN phan_quyen
                ON phan_quyen.ma_nguoi_dung = nguoi_dung.ma_nguoi_dung
            LEFT JOIN quyen
                ON quyen.ma_quyen = phan_quyen.ma_quyen
            WHERE
                ( ten_dang_nhap LIKE ?
                OR email LIKE ?
                OR so_dien_thoai LIKE ? )
                AND quyen.ten_quyen IN ( %s )
            GROUP BY ma_nguoi_dung
            LIMIT ?, ?
        """, dieuKien.getQuyen()
            .stream()
            .map(q -> "'" + q.name() + "'")
            .collect(Collectors.joining(","))));
    }

    @Override
    public void thayDoiTrangThai(Long maNguoiDung, boolean khoa) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                UPDATE nguoi_dung
                SET da_khoa = ?
                WHERE ma_nguoi_dung = ?
            """);
            ps.setBoolean(1, khoa);
            ps.setLong(2, maNguoiDung);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public NguoiDung timNguoiDung(Long ma_nguoi_dung) {
		// TODO Auto-generated method stub
		NguoiDung customer = new NguoiDung();
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * from nguoi_dung
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, ma_nguoi_dung);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	customer.setMaNguoiDung(rs.getLong("ma_nguoi_dung"));
            	customer.setTenHienThi(rs.getString("ten_hien_thi"));
            	customer.setTenDangNhap(rs.getString("ten_dang_nhap"));
            	customer.setEmail(rs.getString("email"));
            	customer.setSoDienThoai(rs.getString("so_dien_thoai"));
            	customer.setMatKhau(rs.getString("mat_khau"));
            	customer.setDaKhoa(rs.getBoolean("da_khoa"));
            	customer.setThoiGianTao(Date.from(rs.getTimestamp("thoi_gian_tao").toInstant()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return customer;
	}

    @Override
    public void tangCoGangDangNhap(NguoiDung nguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                UPDATE nguoi_dung
                SET co_gang_dang_nhap = co_gang_dang_nhap + 1
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, nguoiDung.getMaNguoiDung());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void resetCoGangDangNhap(Long maNguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                UPDATE nguoi_dung
                SET co_gang_dang_nhap = 0
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, maNguoiDung);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void capNhatThongTin(NguoiDung nguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                UPDATE nguoi_dung
                SET ten_hien_thi = ?,
                    ten_dang_nhap = ?,
                    email = ?,
                    so_dien_thoai = ?
                WHERE ma_nguoi_dung = ?
            """);
            ps.setString(1, nguoiDung.getTenHienThi());
            ps.setObject(2, nguoiDung.getTenDangNhap());
            ps.setString(3, nguoiDung.getEmail());
            ps.setString(4, nguoiDung.getSoDienThoai());
            ps.setLong(5, nguoiDung.getMaNguoiDung());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Khong the cap nhat thong tin nguoi dung", e);
        }
    }
}
