package cf.laptrinhweb.btl.repository.impl;

import java.sql.Connection;
import java.util.Set;
import java.util.TreeSet;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Map;

import cf.laptrinhweb.btl.entity.BinhLuan;
import cf.laptrinhweb.btl.repository.BinhLuanRepository;

public class BinhLuanRepositoryImpl implements BinhLuanRepository{

	@Override
	public void themBinhLuan(BinhLuan binhLuan) {
		 try (Connection ketNoi = moKetNoi()) {
	            PreparedStatement ps = ketNoi.prepareStatement("""
					INSERT INTO binh_luan (
						noi_dung_binh_luan,
						ma_nguoi_dung,
						ma_san_pham,
						ngay_binh_luan,
						ma_binh_luan_goc
					) VALUES (?, ?, ?, ?, ?)
				""", Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, binhLuan.getNoi_dung_binh_luan());
	            ps.setLong(2, binhLuan.getNguoi_binh_luan().getMaNguoiDung());
	            ps.setLong(3, binhLuan.getSan_pham().getMaSanPham());
	            ps.setTimestamp(4,Timestamp.from(binhLuan.getNgay_binh_luan().toInstant()));
				// setObject de tranh loi khi binh luan goc la null
	            ps.setObject(5, binhLuan.getMa_binh_luan_goc());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            binhLuan.setId(rs.getLong(1));
	        } catch (Exception e) {
	            throw new RuntimeException("Khong the them binh luan", e);
	        }

	}

	@Override
	public void xoaBinhLuan(Long ma_binh_luan,Long ma_nguoi_dung) {
		// TODO Auto-generated method stub
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                delete 
                from binh_luan 
                where ma_binh_luan = ?
                and ma_nguoi_dung = ?
            """);
            ps.setLong(1,ma_binh_luan);
            ps.setLong(2,ma_nguoi_dung);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Khong the xoa", e);
        }

	}

	@Override
	public Map<BinhLuan, List<BinhLuan>> layTatCaBinhLuan(Long ma_san_pham) {
		// TODO Auto-generated method stub
		Map<BinhLuan, List<BinhLuan>> lbl = new TreeMap<>();
		Map<Long,List<BinhLuan>> lbl1 = new TreeMap<>();
		List<BinhLuan> blcon = new ArrayList<>();
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * 
                from binh_luan 
                where ma_san_pham = ?
                order by ngay_binh_luan DESC
            """);
            ps.setLong(1, ma_san_pham);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	BinhLuan bl = new BinhLuan();
            	bl.setId(rs.getLong("ma_binh_luan"));
            	bl.setNoi_dung_binh_luan(rs.getString("noi_dung_binh_luan"));
            	bl.setNguoi_binh_luan(new NguoiDungRepositoryImpl().timNguoiDung(rs.getLong("ma_nguoi_dung")));
            	bl.setSan_pham(new SanPhamRepositoryImpl().timTheoMa(rs.getLong("ma_san_pham")).get());
            	bl.setNgay_binh_luan(Date.from(rs.getTimestamp("ngay_binh_luan").toInstant()));
            	bl.setMa_binh_luan_goc(rs.getLong("ma_binh_luan_goc"));
            	if(bl.getMa_binh_luan_goc() == 0) {
            		lbl1.put(bl.getId(), new ArrayList<BinhLuan>());
            	}
            	else {
            		blcon.add(bl);
            	}

            }
            for(BinhLuan bl : blcon) {
            	if(lbl1.containsKey(bl.getMa_binh_luan_goc())){
            		List<BinhLuan> newList = lbl1.get(bl.getMa_binh_luan_goc());
            		newList.add(bl);
            		lbl1.put(bl.getMa_binh_luan_goc(), newList);
            	}
            }


            Set<Long> newSet = new TreeSet<>(lbl1.keySet());
            for(Long k : newSet) {
        		lbl.put(this.layBinhLuan(k), lbl1.get(k));
        	}
        } catch (Exception e) {
            throw new RuntimeException("Khong the them tra loi", e);
        }

		return lbl;

	}

	@Override
	public List<BinhLuan> latTatCaBinhLuanTraLoi(Long ma_binh_luan) {
		List<BinhLuan> lbl = new ArrayList<>();
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * 
                from binh_luan 
                where ma_binh_luan_goc = ?
                ORDER BY ngay_binh_luan DESC 
            """);
            ps.setLong(1, ma_binh_luan);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	BinhLuan bl = new BinhLuan();
            	bl.setId(rs.getLong("ma_binh_luan"));
            	bl.setNoi_dung_binh_luan(rs.getString("noi_dung_binh_luan"));
            	bl.setNguoi_binh_luan(new NguoiDungRepositoryImpl().timNguoiDung(rs.getLong("ma_nguoi_dung")));
            	bl.setSan_pham(new SanPhamRepositoryImpl().timTheoMa(rs.getLong("ma_san_pham")).get());
            	bl.setNgay_binh_luan(Date.from(rs.getTimestamp("ngay_binh_luan").toInstant()));
            	bl.setMa_binh_luan_goc(rs.getLong("ma_binh_luan_goc"));
            	lbl.add(bl);
            }
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay tra loi", e);
        }
		return lbl;

	}

	@Override
	public BinhLuan layBinhLuan(Long ma_binh_luan) {
		// TODO Auto-generated method stub
		BinhLuan bl = new BinhLuan();
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * 
                from binh_luan 
                where ma_binh_luan= ?
            """);
            ps.setLong(1, ma_binh_luan);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	bl.setId(rs.getLong("ma_binh_luan"));
            	bl.setNoi_dung_binh_luan(rs.getString("noi_dung_binh_luan"));
            	bl.setNguoi_binh_luan(new NguoiDungRepositoryImpl().timNguoiDung(rs.getLong("ma_nguoi_dung")));
            	bl.setSan_pham(new SanPhamRepositoryImpl().timTheoMa(rs.getLong("ma_san_pham")).get());
            	bl.setNgay_binh_luan(Date.from(rs.getTimestamp("ngay_binh_luan").toInstant()));
            	bl.setMa_binh_luan_goc(rs.getLong("ma_binh_luan_goc"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay tra loi", e);
        }
        return bl;
	}
}
