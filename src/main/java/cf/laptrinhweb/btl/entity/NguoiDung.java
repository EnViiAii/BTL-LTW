package cf.laptrinhweb.btl.entity;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung {
    private Long maNguoiDung;
    private String tenHienThi;
    private String tenDangNhap;
    private String email;
    private String soDienThoai;
    private String matKhau;
    private Date thoiGianTao;
    private boolean daKhoa;
    private int coGangDangNhap;
    @Builder.Default
    private List<Quyen> dsQuyen = new ArrayList<>();

    public boolean coQuyen(QuyenNguoiDung quyen) {
        return this.dsQuyen.stream().anyMatch(q -> q.getTenQuyen().equals(quyen.name()));
    }

    @Override
    public String toString() {
        return "[ " +
            "tenHienThi => '" + tenHienThi + '\'' +
            ", tenDangNhap => '" + tenDangNhap + '\'' +
            ", email => '" + email + '\'' +
            ", soDienThoai => '" + soDienThoai + '\'' +
            '}';
    }
}
