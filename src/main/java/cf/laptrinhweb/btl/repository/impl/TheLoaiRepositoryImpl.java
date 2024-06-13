package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.TheLoai;
import cf.laptrinhweb.btl.mapper.TheLoaiMapper;
import cf.laptrinhweb.btl.repository.TheLoaiRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TheLoaiRepositoryImpl implements TheLoaiRepository {
    @Override
    public List<TheLoai> layTatCa() {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT * FROM the_loai
            """);
            ResultSet resultSet = ps.executeQuery();
            List<TheLoai> dsTheLoai = new ArrayList<>();
            TheLoaiMapper mapper = new TheLoaiMapper();
            while (resultSet.next()) {
                dsTheLoai.add(mapper.map(resultSet));
            }
            return dsTheLoai;
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay danh sach the loai", e);
        }
    }

	@Override
	public TheLoai timTheLoai(Long ma_the_loai) {
		// TODO Auto-generated method stub
		TheLoai a = new TheLoai();
		try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                select * from the_loai
                WHERE ma_the_loai = ?
            """);
            ps.setLong(1, ma_the_loai);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	a.setMaTheLoai(rs.getLong("ma_the_loai"));
            	a.setTenTheLoai(rs.getString("ten_the_loai"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return a;
	}
}
