package cf.laptrinhweb.btl.entity;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quyen {
    private Long maQuyen;
    private String tenQuyen;
    private String moTa;

    public Quyen(QuyenNguoiDung quyenNguoiDung) {
        this.tenQuyen = quyenNguoiDung.name();
    }
}
