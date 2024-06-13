package cf.laptrinhweb.btl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {
    private Long maSanPham;
    private String tenSanPham;
    private String anhXemTruoc;
    private String moTa;
    private TheLoai theLoai;
    private Double gia;
    private Integer soLuong;
    private List<AnhSanPham> danhSachAnh;
    private ChatLieu chatLieu;
    private ThuongHieu thuongHieu;
    private String kichThuoc;
    private Double trongLuong;
    private boolean daAn;
    private int soDanhGia;
    private double diemTrungBinh;
}
