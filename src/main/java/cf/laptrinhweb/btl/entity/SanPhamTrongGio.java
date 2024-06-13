package cf.laptrinhweb.btl.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SanPhamTrongGio {
    private Long maMucGioHang;
    private NguoiDung nguoiDung;
    private SanPham sanPham;
    private Integer soLuong;
}
