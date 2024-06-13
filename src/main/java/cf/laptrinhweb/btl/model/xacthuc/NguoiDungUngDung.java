package cf.laptrinhweb.btl.model.xacthuc;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import cf.laptrinhweb.btl.entity.NguoiDung;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NguoiDungUngDung {
    private Long maNguoiDung;
    private List<QuyenNguoiDung> danhSachQuyen;

    public NguoiDungUngDung(NguoiDung nguoiDung) {
        this.maNguoiDung = nguoiDung.getMaNguoiDung();
        this.danhSachQuyen = nguoiDung.getDsQuyen()
            .stream()
            .map(q -> QuyenNguoiDung.valueOf(q.getTenQuyen()))
            .toList();
    }
}
