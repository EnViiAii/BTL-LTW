package cf.laptrinhweb.btl.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cf.laptrinhweb.btl.entity.DanhGia;
import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.SanPham;
import cf.laptrinhweb.btl.entity.SanPhamDat;
import cf.laptrinhweb.btl.repository.DanhGiaRepository;
import cf.laptrinhweb.btl.service.impl.DatHangServiceImpl;
import cf.laptrinhweb.btl.service.impl.SanPhamDatServiceImpl;
import cf.laptrinhweb.btl.service.impl.SanPhamServiceImpl;

public class DanhGiaRepositoryImpl implements DanhGiaRepository {

	@Override
	public void themDanhGia(DanhGia danhGia,Long ma_san_pham_dat) {
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO danh_gia (
            		ma_nguoi_danh_gia,
                    ma_san_pham_dat,
                    diem_danh_gia,
                    noi_dung_danh_gia,
                    ngay_danh_gia
                ) VALUES (?,?,?,?,?)
            """,Statement.RETURN_GENERATED_KEYS);
           
            ps.setLong(1, danhGia.getKhachHangDanhGia().getMaNguoiDung());
         
            ps.setLong(2, ma_san_pham_dat);
            
            ps.setInt(3,danhGia.getSoDiemDanhGia());
          
            ps.setString(4, danhGia.getNoi_dung_danh_gia());
         
            ps.setTimestamp(5, Timestamp.from(danhGia.getNgay_danh_gia().toInstant()));
            ps.executeUpdate();
         
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            danhGia.setId(rs.getLong(1));
            Long ma_san_pham = new SanPhamDatRepositoryImpl().timMaSanPham(ma_san_pham_dat);
            SanPham sp = new SanPhamRepositoryImpl().timTheoMa(ma_san_pham).get();
            this.capNhatDanhGia(danhGia, sp.getMaSanPham());
       
        } catch (Exception e) {
            throw new RuntimeException("Khong the them danh gia", e);
        }
		
	}

	@Override
	public void xoaDanhGia(Long ma_danh_gia) {
		// TODO Auto-generated method stub
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                delete from danh_gia where ma_danh_gia = ?""");
            ps.setLong(1, ma_danh_gia);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Khong the xoa danh gia", e);
        }
		
		
	}

	@Override
	public List<DanhGia> layTatCaDanhGia(Long ma_san_pham) {
		List<DanhGia> ldg = new ArrayList<>();
		// TODO Auto-generated method stub
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * 
                from danh_gia,san_pham_dat,san_pham
                where danh_gia.ma_san_pham_dat = san_pham_dat.ma_san_pham_dat
                and san_pham_dat.ma_san_pham = san_pham.ma_san_pham
                and san_pham_dat.ma_san_pham = ?
                order by ngay_danh_gia DESC
                """);
            ps.setLong(1, ma_san_pham);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	DanhGia a = new DanhGia();
            	a.setId(rs.getLong("ma_danh_gia"));
            	a.setKhachHangDanhGia(new NguoiDungRepositoryImpl().timNguoiDung(rs.getLong("ma_nguoi_danh_gia")));
            	a.setNoi_dung_danh_gia(rs.getString("noi_dung_danh_gia"));
            	a.setSoDiemDanhGia(rs.getInt("diem_danh_gia"));
            	a.setNgay_danh_gia(Date.from(rs.getTimestamp("ngay_danh_gia").toInstant()));
            	SanPhamDat spd = new SanPhamDat();
            	spd.setId(rs.getLong("ma_san_pham_dat"));
            	spd.setGia(rs.getDouble("don_gia"));
            	spd.setSoLuong(rs.getInt("so_luong"));
            	spd.setSanPham(new SanPhamRepositoryImpl().timTheoMa(rs.getLong("ma_san_pham")).get());
            	a.setSan_pham_dat(spd);
            	ldg.add(a);
            }
        } catch (Exception e) {
            throw new RuntimeException("Khong the xoa danh gia", e);
        }
		return ldg;
	}

	@Override
	public void capNhatDanhGia(DanhGia dg, Long maSanPham) {
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                update san_pham
                set diem_trung_binh = (diem_trung_binh * so_danh_gia + ?) / (so_danh_gia + 1),
                    so_danh_gia = so_danh_gia + 1
                where ma_san_pham = ?
            """);
            ps.setDouble(1,dg.getSoDiemDanhGia());
            ps.setLong(2, maSanPham);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		
	}
	

}
