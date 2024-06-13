package cf.laptrinhweb.btl.dto.request;

import cf.laptrinhweb.btl.constant.QuyenNguoiDung;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PhanQuyenDTO {
    private Long maNguoiDung;
    private Set<QuyenNguoiDung> danhSachQuyen = new HashSet<>();
}
