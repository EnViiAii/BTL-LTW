package cf.laptrinhweb.btl.repository.impl;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.PhanQuyen;
import cf.laptrinhweb.btl.mapper.QuyenMapper;
import cf.laptrinhweb.btl.entity.Quyen;
import cf.laptrinhweb.btl.repository.PhanQuyenRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class PhanQuyenRepositoryImpl implements PhanQuyenRepository {
    private final QuyenMapper quyenMapper = new QuyenMapper();

    @Override
    public void themQuyenChoNguoiDung(Long maNguoiDung, List<Long> quyenDuocPhan) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                INSERT INTO phan_quyen (ma_nguoi_dung, ma_quyen)
                VALUES (?, ?)
            """);
            for (long maQuyen : quyenDuocPhan) {
                ps.setLong(1, maNguoiDung);
                ps.setLong(2, maQuyen);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Quyen> timBangMaNguoiDung(Long maNguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            PreparedStatement ps = ketNoi.prepareStatement("""
                SELECT *
                FROM phan_quyen pq
                JOIN quyen q on pq.ma_quyen = q.ma_quyen
                WHERE ma_nguoi_dung = ?
            """);
            ps.setLong(1, maNguoiDung);
            ResultSet dsKetQua = ps.executeQuery();
            List<Quyen> dsQuyen = new ArrayList<>();
            while (dsKetQua.next()) {
                dsQuyen.add(quyenMapper.map(dsKetQua));
            }
            return dsQuyen;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void huyQuyenNguoiDung(Long maNguoiDung, List<Long> quyenDeHuy) {
        try (Connection ketNoi = moKetNoi()) {
            String query = """
                DELETE FROM phan_quyen
                WHERE ma_nguoi_dung = ?
                AND ma_quyen IN
            """;
            StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
            quyenDeHuy.forEach(q -> {
                stringJoiner.add(q.toString());
            });
            query += stringJoiner;
            PreparedStatement ps = ketNoi.prepareStatement(query);
            ps.setLong(1, maNguoiDung);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PhanQuyen> layTheoDanhSachNguoiDung(List<NguoiDung> dsNguoiDung) {
        try (Connection ketNoi = moKetNoi()) {
            String tapMaNguoiDung = dsNguoiDung.stream().map(nguoiDung -> nguoiDung.getMaNguoiDung().toString()).collect(Collectors.joining(", "));
            PreparedStatement ps = ketNoi.prepareStatement(String.format("""
                SELECT *
                FROM phan_quyen
                JOIN quyen
                    ON phan_quyen.ma_quyen = quyen.ma_quyen
                WHERE ma_nguoi_dung IN ( %s )
            """, tapMaNguoiDung));
            ResultSet resultSet = ps.executeQuery();
            List<PhanQuyen> dsPhanQuyen = new ArrayList<>();
            QuyenMapper quyenMapper = new QuyenMapper();
            while (resultSet.next()) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setMaNguoiDung(resultSet.getLong("ma_nguoi_dung"));
                phanQuyen.setQuyen(quyenMapper.map(resultSet));
                dsPhanQuyen.add(phanQuyen);
            }
            return dsPhanQuyen;
        } catch (Exception e) {
            throw new RuntimeException("Khong the dong bo phan quyen", e);
        }
    }
}
