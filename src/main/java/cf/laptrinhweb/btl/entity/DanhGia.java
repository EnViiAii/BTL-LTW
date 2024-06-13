package cf.laptrinhweb.btl.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DanhGia {
    private Long id;
    private NguoiDung khachHangDanhGia;
    private String noi_dung_danh_gia;
    private Integer soDiemDanhGia;
    private Date ngay_danh_gia;
    private SanPhamDat san_pham_dat;
}
