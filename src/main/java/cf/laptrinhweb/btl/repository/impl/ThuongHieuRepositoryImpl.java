package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.TheLoai;
import cf.laptrinhweb.btl.entity.ThuongHieu;
import cf.laptrinhweb.btl.mapper.TheLoaiMapper;
import cf.laptrinhweb.btl.mapper.ThuongHieuMapper;
import cf.laptrinhweb.btl.repository.ThuongHieuRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThuongHieuRepositoryImpl implements ThuongHieuRepository {
    @Override
    public List<ThuongHieu> layTatCa() {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT * FROM thuong_hieu
            """);
            ResultSet resultSet = ps.executeQuery();
            List<ThuongHieu> dsThuongHieu = new ArrayList<>();
            ThuongHieuMapper mapper = new ThuongHieuMapper();
            while (resultSet.next()) {
                dsThuongHieu.add(mapper.map(resultSet));
            }
            return dsThuongHieu;
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay danh sach the loai", e);
        }
    }

	@Override
	public ThuongHieu timThuongHieu(Long ma_thuong_hieu) {
		// TODO Auto-generated method stub
		ThuongHieu a = new ThuongHieu();
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * from nguoi_dung
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, ma_thuong_hieu);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	a.setMaThuongHieu(rs.getLong("ma_thuong_hieu"));
            	a.setTenThuongHieu(rs.getString("ten_thuong_hieu"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return a;
	}
}
