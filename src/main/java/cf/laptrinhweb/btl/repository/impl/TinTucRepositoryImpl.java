package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.TinTuc;
import cf.laptrinhweb.btl.mapper.TinTucMapper;
import cf.laptrinhweb.btl.repository.TinTucRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TinTucRepositoryImpl implements TinTucRepository {
    @Override
    public List<TinTuc> layTatCa() {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM tin_tuc");
            ResultSet resultSet = ps.executeQuery();
            List<TinTuc> danhSachTinTuc = new ArrayList<>();
            TinTucMapper mapper = new TinTucMapper();
            while (resultSet.next()) {
                danhSachTinTuc.add(mapper.map(resultSet));
            }
            return danhSachTinTuc;
        } catch (Exception e) {
            throw new RuntimeException("Khong the lay danh sach tin tuc", e);
        }
    }
}
