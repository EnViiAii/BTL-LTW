package cf.laptrinhweb.btl.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cf.laptrinhweb.btl.constant.HinhThucThanhToan;
import cf.laptrinhweb.btl.constant.TrangThaiDon;
import cf.laptrinhweb.btl.entity.DatHang;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPhamDat;
import cf.laptrinhweb.btl.mapper.DatHangMapper;
import cf.laptrinhweb.btl.model.DieuKienDonHang;
import cf.laptrinhweb.btl.repository.DatHangRepository;

public class DatHangRepositoryImpl implements DatHangRepository{

	@Override
	public void themDatHang(DatHang datHang) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO dat_hang (
                    hinh_thuc_thanh_toan,
                    phuong_thuc_van_chuyen,
                    trang_thai,
                    dia_chi_giao,
                    ghi_chu,
                    ngay_dat_hang,
                    ma_nguoi_dat,
                    ten_nguoi_nhan,
                    sdt_nhan,
                    tong_tien
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, datHang.getHinhThucThanhToan() == HinhThucThanhToan.THANH_TOAN_KHI_NHAN ? 1 : 2);
            ps.setInt(2, datHang.getPhuongThucVanChuyen());
            ps.setInt(3, datHang.getTinhTrang().getGiaTri());
            ps.setString(4, datHang.getDiaChiGiao());
            ps.setString(5, datHang.getGhiChu());
            ps.setTimestamp(6, Timestamp.valueOf(datHang.getNgayTaoDon()));
            ps.setLong(7, datHang.getNguoiDung().getMaNguoiDung());
            ps.setString(8,datHang.getTenNguoiNhan());
            ps.setString(9, datHang.getSdtNhan());
            ps.setDouble(10, datHang.getTongTien());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            resultSet.next();
            datHang.setMaDatHang(resultSet.getLong(1));
        } catch (Exception e) {
            throw new RuntimeException("Khong the them sp vao gio hang", e);
        }
	}

	@Override
	public List<DatHang> layTatCaCuaNguoiDung(NguoiDung nguoidung) {
		// TODO Auto-generated method stub
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM dat_hang
                WHERE ma_nguoi_dat = ?
            	ORDER BY ngay_dat_hang DESC
            """);
            ps.setLong(1, nguoidung.getMaNguoiDung());
            ResultSet resultSet = ps.executeQuery();
            List<DatHang> res = new ArrayList<>();
            while( resultSet.next() ) {
            	DatHang dh = new DatHang();
            	dh.setDiaChiGiao(resultSet.getString("dia_chi_giao"));
            	dh.setMaDatHang(resultSet.getLong("ma_dat_hang"));
            	dh.setHinhThucThanhToan(resultSet.getInt("hinh_thuc_thanh_toan") == 1 ? HinhThucThanhToan.THANH_TOAN_KHI_NHAN : HinhThucThanhToan.THE_NGAN_HANG);
            	dh.setGhiChu(resultSet.getString("ghi_chu"));
            	dh.setPhuongThucVanChuyen(resultSet.getInt("phuong_thuc_van_chuyen"));
            	dh.setSdtNhan(resultSet.getString("sdt_nhan"));
            	dh.setNguoiDung(nguoidung);
            	dh.setTenNguoiNhan(resultSet.getString("ten_nguoi_nhan"));
            	dh.setTinhTrang(TrangThaiDon.cuaGiaTri(resultSet.getInt("trang_thai")));
            	dh.setNgayTaoDon(resultSet.getTimestamp("ngay_dat_hang").toLocalDateTime());
            	dh.setTongTien(resultSet.getDouble("tong_tien"));
            	dh.setDanhSachSanPham(new SanPhamDatRepositoryImpl().layTatCaTheoMaDat(dh.getMaDatHang(), nguoidung));
            	res.add(dh);
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException("Khong the truy van dat hang", e);
        }
	}

	@Override
	public DatHang layDatHang(Long ma_dat_hang) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM dat_hang
                WHERE ma_dat_hang = ?
            """);
            ps.setLong(1, ma_dat_hang);
            ResultSet resultSet = ps.executeQuery();
            DatHang dh = new DatHang();
            if(resultSet.next()) {
            	dh.setDiaChiGiao(resultSet.getString("dia_chi_giao"));
            	dh.setMaDatHang(resultSet.getLong("ma_dat_hang"));
            	dh.setHinhThucThanhToan(resultSet.getInt("hinh_thuc_thanh_toan") == 1 ? HinhThucThanhToan.THANH_TOAN_KHI_NHAN : HinhThucThanhToan.THE_NGAN_HANG);
            	dh.setGhiChu(resultSet.getString("ghi_chu"));
            	dh.setPhuongThucVanChuyen(resultSet.getInt("phuong_thuc_van_chuyen"));
            	dh.setSdtNhan(resultSet.getString("sdt_nhan"));
            	dh.setNguoiDung(new NguoiDungRepositoryImpl().timNguoiDung(resultSet.getLong("ma_nguoi_dat")));
            	dh.setTenNguoiNhan(resultSet.getString("ten_nguoi_nhan"));
            	dh.setTinhTrang(TrangThaiDon.cuaGiaTri(resultSet.getInt("trang_thai")));
            	dh.setNgayTaoDon(resultSet.getTimestamp("ngay_dat_hang").toLocalDateTime());
            	dh.setTongTien(resultSet.getDouble("tong_tien"));
            	dh.setDanhSachSanPham(new SanPhamDatRepositoryImpl().layTatCaTheoMaDat(dh.getMaDatHang(), dh.getNguoiDung()));
            }
            return dh;
        } catch (Exception e) {
            throw new RuntimeException("Khong the truy van dat hang", e);
        }
    }

    public DatHang layDonTheoMaDatHang(Long maDatHang, NguoiDung nguoidung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM dat_hang
                WHERE ma_dat_hang = ?
                AND ma_nguoi_dat = ?
            """);
            ps.setLong(1, maDatHang);
            ps.setLong(2, nguoidung.getMaNguoiDung());
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
        	DatHang dh = new DatHang();
        	dh.setDiaChiGiao(resultSet.getString("dia_chi_giao"));
        	dh.setMaDatHang(resultSet.getLong("ma_dat_hang"));
        	dh.setHinhThucThanhToan(resultSet.getInt("hinh_thuc_thanh_toan") == 1 ? HinhThucThanhToan.THANH_TOAN_KHI_NHAN : HinhThucThanhToan.THE_NGAN_HANG);
        	dh.setGhiChu(resultSet.getString("ghi_chu"));
        	dh.setPhuongThucVanChuyen(resultSet.getInt("phuong_thuc_van_chuyen"));
        	dh.setSdtNhan(resultSet.getString("sdt_nhan"));
        	dh.setNguoiDung(nguoidung);
        	dh.setTenNguoiNhan(resultSet.getString("ten_nguoi_nhan"));
        	dh.setTinhTrang(TrangThaiDon.cuaGiaTri(resultSet.getInt("trang_thai")));
        	dh.setNgayTaoDon(resultSet.getTimestamp("ngay_dat_hang").toLocalDateTime());
        	dh.setTongTien(resultSet.getDouble("tong_tien"));
        	dh.setDanhSachSanPham(new SanPhamDatRepositoryImpl().layTatCaTheoMaDat(maDatHang, nguoidung));
            return dh;
        } catch (Exception e) {
            throw new RuntimeException("Khong the truy van dat hang", e);
        }
	}

    @Override
    public List<DatHang> locTheoDieuKien(DieuKienDonHang dieuKien) {
        try (Connection ketNoi = moKetNoi()) {
            String truyVan = """
                SELECT *
                FROM dat_hang
                WHERE trang_thai IN (%s)
                ORDER BY %s %s
                LIMIT ?, ?
            """;
            String dieuKienTrangThai = dieuKien.getDsTrangThai()
                .stream()
                .map(a -> a.getGiaTri().toString())
                .collect(Collectors.joining(", "));
            truyVan = String.format(
                truyVan,
                dieuKienTrangThai,
                dieuKien.getKieuSapXep().getCot(),
                dieuKien.getKieuSapXep().getHuongSapXep()
            );
            PreparedStatement ps = ketNoi.prepareStatement(truyVan);
            ps.setInt(1, dieuKien.getTrang() * dieuKien.getKichThuoc());
            ps.setInt(2, dieuKien.getKichThuoc());
            ResultSet resultSet = ps.executeQuery();
            List<DatHang> dsDonHang = new ArrayList<>();
            DatHangMapper mapper = new DatHangMapper();
            while (resultSet.next()) {
                dsDonHang.add(mapper.map(resultSet));
            }
            return dsDonHang;
        } catch (Exception e) {
            throw new RuntimeException("Khong the loc san pham", e);
        }
    }
    
    public DatHang layDonTheoMaBoiQuanLy(Long maDatHang) {
    	try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM dat_hang
                WHERE ma_dat_hang = ?
            	ORDER BY ngay_dat_hang DESC
            """);
            ps.setLong(1, maDatHang);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
        	DatHang dh = new DatHang();
        	dh.setDiaChiGiao(resultSet.getString("dia_chi_giao"));
        	dh.setMaDatHang(resultSet.getLong("ma_dat_hang"));
        	dh.setHinhThucThanhToan(resultSet.getInt("hinh_thuc_thanh_toan") == 1 ? HinhThucThanhToan.THANH_TOAN_KHI_NHAN : HinhThucThanhToan.THE_NGAN_HANG);
        	dh.setGhiChu(resultSet.getString("ghi_chu"));
        	dh.setPhuongThucVanChuyen(resultSet.getInt("phuong_thuc_van_chuyen"));
        	dh.setSdtNhan(resultSet.getString("sdt_nhan"));
        	dh.setTenNguoiNhan(resultSet.getString("ten_nguoi_nhan"));
        	dh.setTinhTrang(TrangThaiDon.cuaGiaTri(resultSet.getInt("trang_thai")));
        	dh.setNgayTaoDon(resultSet.getTimestamp("ngay_dat_hang").toLocalDateTime());
        	dh.setTongTien(resultSet.getDouble("tong_tien"));
        	dh.setDanhSachSanPham(new SanPhamDatRepositoryImpl().layTatCaTheoMaDatBoiQuanLy(maDatHang));
            return dh;
        } catch (Exception e) {
            throw new RuntimeException("Khong the truy van dat hang", e);
        }
    }

	@Override
	public void CapNhatTrangThaiDon(Long maDatHang, TrangThaiDon trangThaiMoi) {
		try (Connection ketNoi = moKetNoi()){
			PreparedStatement ps = ketNoi.prepareStatement("""
	                UPDATE dat_hang
	                SET trang_thai = ?
	                WHERE ma_dat_hang = ?
	            """);
			ps.setInt(1, trangThaiMoi.getGiaTri());
			ps.setLong(2, maDatHang);
			ps.executeUpdate();
			
		}catch(Exception e) {
			throw new RuntimeException("Khong the cap nhat trang thai don",e);
		}
		
	}
}
