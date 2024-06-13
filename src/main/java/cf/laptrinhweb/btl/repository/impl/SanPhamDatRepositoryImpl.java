package cf.laptrinhweb.btl.repository.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import cf.laptrinhweb.btl.constant.HinhThucThanhToan;
import cf.laptrinhweb.btl.entity.DatHang;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.entity.SanPhamDat;
import cf.laptrinhweb.btl.mapper.SanPhamMapper;
import cf.laptrinhweb.btl.repository.SanPhamDatRepository;

public class SanPhamDatRepositoryImpl implements SanPhamDatRepository{
	@Override
	public void themSanPhamDat(DatHang datHang) {
		try (Connection ketNoi = moKetNoi()) {
			for (SanPhamDat sanpham : datHang.getDanhSachSanPham()) {
	            PreparedStatement ps = ketNoi.prepareStatement("""
					INSERT INTO san_pham_dat (
						ma_san_pham,
						don_gia,
						so_luong,
						ma_dat_hang
					) VALUES (?, ?, ?, ?)
				""", Statement.RETURN_GENERATED_KEYS);
	            ps.setDouble(2, sanpham.getGia());
	            ps.setLong(1, sanpham.getSanPham().getMaSanPham());
	            ps.setInt(3, sanpham.getSoLuong());
	            ps.setLong(4, datHang.getMaDatHang());
	            ps.executeUpdate();
	            ResultSet resultSet = ps.getGeneratedKeys();
	            resultSet.next();
	            sanpham.setId(resultSet.getLong(1));
			}
        } catch (Exception e) {
            throw new RuntimeException("Khong the them sp_dat vao gio hang", e);
        }
	}

	@Override
	public List<SanPhamDat> layTatCaTheoMaDat(Long maDatHang, NguoiDung nguoidung) {
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM san_pham_dat, dat_hang
                WHERE san_pham_dat.ma_dat_hang = ?
            		    AND san_pham_dat.ma_dat_hang = dat_hang.ma_dat_hang
            """);
            ps.setLong(1, maDatHang);
            ResultSet resultSet = ps.executeQuery();
            List<SanPhamDat> res = new ArrayList<>();
            while( resultSet.next() ) {
            	SanPhamDat tmp = new SanPhamDat();
            	tmp.setGia(resultSet.getDouble("don_gia"));
            	tmp.setId(resultSet.getLong("ma_san_pham_dat"));
            	tmp.setSoLuong(resultSet.getInt("so_luong"));
            	tmp.setSanPham(new SanPhamRepositoryImpl().timTheoMa(resultSet.getLong("ma_san_pham")).get());
            	res.add(tmp);
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException("Khong the tim san pham theo ma", e);
        }
	}
	
	public List<SanPhamDat> layTatCaTheoMaDatBoiQuanLy(Long maDatHang) {
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM san_pham_dat, dat_hang
                WHERE san_pham_dat.ma_dat_hang = ?
            		    AND san_pham_dat.ma_dat_hang = dat_hang.ma_dat_hang
            """);
            ps.setLong(1, maDatHang);
            ResultSet resultSet = ps.executeQuery();
            List<SanPhamDat> res = new ArrayList<>();
            while( resultSet.next() ) {
            	SanPhamDat tmp = new SanPhamDat();
            	tmp.setGia(resultSet.getDouble("don_gia"));
            	tmp.setId(resultSet.getLong("ma_san_pham_dat"));
            	tmp.setSoLuong(resultSet.getInt("so_luong"));
            	tmp.setSanPham(new SanPhamRepositoryImpl().timTheoMa(resultSet.getLong("ma_san_pham")).get());
            	res.add(tmp);
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException("Khong the tim san pham theo ma", e);
        }
	}

	@Override
	public Long timMaSanPham(Long ma_san_pham_dat) {
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
               select ma_san_pham
               from san_pham_dat
               where ma_san_pham_dat = ?
            """);
            ps.setLong(1, ma_san_pham_dat);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) 
            	return rs.getLong("ma_san_pham");
        } catch (Exception e) {
            throw new RuntimeException("Khong the them sp_dat vao gio hang", e);
        }
		return null;
	}
}
