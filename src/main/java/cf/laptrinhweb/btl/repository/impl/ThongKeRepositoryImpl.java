package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.constant.TrangThaiDon;
import cf.laptrinhweb.btl.model.*;
import cf.laptrinhweb.btl.repository.ThongKeRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongKeRepositoryImpl implements ThongKeRepository {
    @Override
    public Map<LocalDate, Double> thongKeDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT
                	DATE(ngay_dat_hang) AS ngay_dat,
                    SUM(tong_tien) AS tong_tien
                FROM dat_hang
                WHERE
                    trang_thai <> ?
                	AND DATE(ngay_dat_hang) >= ?
                    AND DATE(ngay_dat_hang) <= ?
                GROUP BY ngay_dat;
            """);
            ps.setInt(1, TrangThaiDon.DA_HUY.getGiaTri());
            ps.setDate(2, Date.valueOf(ngayBatDau));
            ps.setDate(3, Date.valueOf(ngayKetThuc));
            ResultSet resultSet = ps.executeQuery();
            Map<LocalDate, Double> cacThongKe = new HashMap<>();
            while (resultSet.next()) {
                LocalDate ngayDuocThongKe = resultSet.getDate("ngay_dat").toLocalDate();
                Double tongDoanhThu = resultSet.getDouble("tong_tien");
                cacThongKe.put(ngayDuocThongKe, tongDoanhThu);
            }
            return cacThongKe;
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay du lieu thong ke doanh thu", e);
        }
    }

    @Override
    public double layGiaTriDonTrungBinh(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT
                	AVG(tong_tien) AS trung_binh_don
                FROM dat_hang
                WHERE
                    trang_thai <> ?
                	AND DATE(ngay_dat_hang) >= ?
                    AND DATE(ngay_dat_hang) <= ?
            """);
            ps.setInt(1, TrangThaiDon.DA_HUY.getGiaTri());
            ps.setDate(2, Date.valueOf(ngayBatDau));
            ps.setDate(3, Date.valueOf(ngayKetThuc));
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getDouble("trung_binh_don");
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay du lieu thong ke doanh thu", e);
        }
    }

    @Override
    public TiLeDonHuy tinhTiLeHuyDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT
                	COUNT(*) AS tong_so
                FROM dat_hang
                WHERE
                    dat_hang.trang_thai = ?
                	AND DATE(ngay_dat_hang) >= ?
                    AND DATE(ngay_dat_hang) <= ?
            """);

            ps.setInt(1, TrangThaiDon.DA_HUY.getGiaTri());
            ps.setDate(2, Date.valueOf(ngayBatDau));
            ps.setDate(3, Date.valueOf(ngayKetThuc));
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            int soDonHuy = resultSet.getInt("tong_so");

            ps.setInt(1, TrangThaiDon.HOAN_TAT.getGiaTri());
            ps.setDate(2, Date.valueOf(ngayBatDau));
            ps.setDate(3, Date.valueOf(ngayKetThuc));
            resultSet = ps.executeQuery();
            resultSet.next();
            int soDonThanhCong = resultSet.getInt("tong_so");

            ps = ketNoi.prepareStatement("""
                SELECT
                	COUNT(*) AS tong_so
                FROM dat_hang
                WHERE
                	DATE(ngay_dat_hang) >= ?
                    AND DATE(ngay_dat_hang) <= ?
            """);
            ps.setDate(1, Date.valueOf(ngayBatDau));
            ps.setDate(2, Date.valueOf(ngayKetThuc));
            resultSet = ps.executeQuery();
            resultSet.next();
            int tongSoDon = resultSet.getInt("tong_so");

            return TiLeDonHuy.builder()
                .soDonHuy(soDonHuy)
                .soDonThanhCong(soDonThanhCong)
                .conLai(tongSoDon - soDonHuy - soDonThanhCong)
                .build();
        } catch (Exception e) {
            throw new RuntimeException("Khong the tinh toan ti le huy don", e);
        }
    }
    
    @Override
    public List<SanPhamMuaNhieu> layTopSanPhamBanChay(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
               SELECT
            		sum(d.so_luong) AS so_luong_mua, s.ten_san_pham
            	FROM san_pham_dat d
            	JOIN san_pham s ON d.ma_san_pham = s.ma_san_pham
	            JOIN dat_hang dh ON d.ma_dat_hang = dh.ma_dat_hang
                WHERE DATE(ngay_dat_hang) >= ? AND DATE(ngay_dat_hang) <= ?
            	GROUP BY d.ma_san_pham, s.ten_san_pham
            	ORDER BY so_luong_mua DESC
            	LIMIT 3
            """);
            ps.setDate(1, Date.valueOf(ngayBatDau));
            ps.setDate(2, Date.valueOf(ngayKetThuc));

            ResultSet resultSet = ps.executeQuery();
            List<SanPhamMuaNhieu> dsSP = new ArrayList<>();
            int dem = 1;
            while (resultSet.next()) {
                SanPhamMuaNhieu sp = new SanPhamMuaNhieu();
                sp.setStt(dem++);
                sp.setSoLuong(resultSet.getInt("so_luong_mua"));
                sp.setTenSanPham(resultSet.getString("ten_san_pham"));
                dsSP.add(sp);
            }
            return dsSP;
        } catch (Exception e) {
            throw new RuntimeException("San pham moi cap nhat", e);
        }
    }
    @Override
    public List<KhachHangMuaNhieu> layTopKhachMuaNhieu(LocalDate ngayBatDau, LocalDate ngayKetThuc){
    	  try (Connection ketNoi = moKetNoi()) {
              PreparedStatement ps = ketNoi.prepareStatement("""
                  SELECT n.ten_dang_nhap,n.ten_hien_thi,sum(d.tong_tien) as tt FROM dat_hang d 
                  JOIN nguoi_dung n ON d.ma_nguoi_dat = n.ma_nguoi_dung
                  WHERE DATE(ngay_dat_hang) >= ? AND DATE(ngay_dat_hang) <= ?
                  GROUP BY  d.ma_nguoi_dat
                  ORDER BY tt DESC
                  LIMIT 3;
              """);
              ps.setDate(1, Date.valueOf(ngayBatDau));
              ps.setDate(2, Date.valueOf(ngayKetThuc));

              ResultSet resultSet = ps.executeQuery();
              List<KhachHangMuaNhieu> dsKH = new ArrayList<>();
              int dem = 1;
              while (resultSet.next()) {
                  KhachHangMuaNhieu kh = new KhachHangMuaNhieu();
                  kh.setStt(dem++);
                  kh.setTenDangNhap(resultSet.getString("ten_dang_nhap"));
                  kh.setTenHienThi(resultSet.getString("ten_hien_thi"));
                  kh.setTongTien(resultSet.getDouble("tt"));
                  dsKH.add(kh);
             
              }
              return dsKH;
          } catch (Exception e) {
              throw new RuntimeException("Khach hang moi", e);
          }
    }
    
    @Override
    public List<TheLoaiMuaNhieu> layTheLoaiBanChay(LocalDate ngayBatDau, LocalDate ngayKetThuc){
    	  try (Connection ketNoi = moKetNoi()) {
              PreparedStatement ps = ketNoi.prepareStatement("""
                  SELECT tl.ten_the_loai, SUM(spd.so_luong) AS tong_so_luong
                  FROM the_loai tl
                  INNER JOIN san_pham sp ON tl.ma_the_loai = sp.ma_the_loai
                  INNER JOIN san_pham_dat spd ON sp.ma_san_pham = spd.ma_san_pham
                  INNER JOIN dat_hang dh ON spd.ma_dat_hang = dh.ma_dat_hang
                  WHERE DATE(dh.ngay_dat_hang) >= ? AND DATE(dh.ngay_dat_hang) <= ?
                  GROUP BY tl.ten_the_loai
                  ORDER BY tong_so_luong DESC
                  LIMIT 3;
              """);
              ps.setDate(1, Date.valueOf(ngayBatDau));
              ps.setDate(2, Date.valueOf(ngayKetThuc));

              ResultSet resultSet = ps.executeQuery();
              List<TheLoaiMuaNhieu> dsTL = new ArrayList<>();
              int dem = 1;
              while (resultSet.next()) {
                  TheLoaiMuaNhieu tl = new TheLoaiMuaNhieu();
                  tl.setStt(dem++);
                  tl.setTenTheLoai(resultSet.getString("ten_the_loai"));
                  tl.setSoLuong(resultSet.getInt("tong_so_luong"));
                  dsTL.add(tl);
             
              }
              return dsTL;
          } catch (Exception e) {
              throw new RuntimeException("The loai moi", e);
          }
    }
    
    @Override
    public List<ThuongHieuMuaNhieu> layThuongHieuBanChay(LocalDate ngayBatDau, LocalDate ngayKetThuc){
    	  try (Connection ketNoi = moKetNoi()) {
              PreparedStatement ps = ketNoi.prepareStatement("""
              		SELECT th.ten_thuong_hieu, SUM(spd.so_luong) AS tong_so_luong
              		FROM thuong_hieu th
              		INNER JOIN san_pham sp ON th.ma_thuong_hieu = sp.ma_thuong_hieu
              		INNER JOIN san_pham_dat spd ON sp.ma_san_pham = spd.ma_san_pham
		            INNER JOIN dat_hang dh ON spd.ma_dat_hang = dh.ma_dat_hang
		            WHERE DATE(dh.ngay_dat_hang) >= ? AND DATE(dh.ngay_dat_hang) <= ?
              		GROUP BY th.ten_thuong_hieu
              		ORDER BY tong_so_luong DESC
              		LIMIT 3;
              """);
              ps.setDate(1, Date.valueOf(ngayBatDau));
              ps.setDate(2, Date.valueOf(ngayKetThuc));

              ResultSet resultSet = ps.executeQuery();
              List<ThuongHieuMuaNhieu> dsTH = new ArrayList<>();
              int dem = 1;
              while (resultSet.next()) {
                  ThuongHieuMuaNhieu th = new ThuongHieuMuaNhieu();
                  th.setStt(dem++);
                  th.setTenThuongHieu(resultSet.getString("ten_thuong_hieu"));
                  th.setSoLuong(resultSet.getInt("tong_so_luong"));
                  dsTH.add(th);
             
              }
              return dsTH;
          } catch (Exception e) {
              throw new RuntimeException("Thuong hieu moi", e);
          }
    }

    @Override
    public Map<LocalDate, Double> thongKeSoDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT
                	DATE(ngay_dat_hang) AS ngay_dat,
                    COUNT(*) AS so_don
                FROM dat_hang
                WHERE
                    trang_thai <> ?
                	AND DATE(ngay_dat_hang) >= ?
                    AND DATE(ngay_dat_hang) <= ?
                GROUP BY ngay_dat;
            """);
            ps.setInt(1, TrangThaiDon.DA_HUY.getGiaTri());
            ps.setDate(2, Date.valueOf(ngayBatDau));
            ps.setDate(3, Date.valueOf(ngayKetThuc));
            ResultSet resultSet = ps.executeQuery();
            Map<LocalDate, Double> cacThongKe = new HashMap<>();
            while (resultSet.next()) {
                LocalDate ngayDuocThongKe = resultSet.getDate("ngay_dat").toLocalDate();
                Double tongDoanhThu = resultSet.getDouble("so_don");
                cacThongKe.put(ngayDuocThongKe, tongDoanhThu);
            }
            return cacThongKe;
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay du lieu thong ke doanh thu", e);
        }
    }
}
