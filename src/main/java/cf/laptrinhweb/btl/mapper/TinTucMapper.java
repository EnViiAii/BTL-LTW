package cf.laptrinhweb.btl.mapper;

import cf.laptrinhweb.btl.entity.TinTuc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TinTucMapper {
    public TinTuc map(ResultSet resultSet) throws SQLException {
        return TinTuc.builder()
            .tieuDe(resultSet.getString("tieu_de"))
            .moTa(resultSet.getString("mo_ta"))
            .nhanNutLienKet(resultSet.getString("nhan_nut_lien_ket"))
            .anh(resultSet.getString("anh"))
            .build();
    }
}
