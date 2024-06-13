package cf.laptrinhweb.btl.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThongTinSanPham {
    private Long maSanPham;
    private String ten;
    private String moTa;
    private String anhXemTruoc;
    private Long maTheLoai;
    private Double gia;
    private Integer soLuong;
    private Long maChatLieu;
    private Long maThuongHieu;
    private String kichThuoc;
    private Double trongLuong;
    private boolean daAn;
}
