package cf.laptrinhweb.btl.entity;

import cf.laptrinhweb.btl.constant.HinhThucThanhToan;
import cf.laptrinhweb.btl.constant.TrangThaiDon;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static cf.laptrinhweb.btl.constant.HinhThucThanhToan.THANH_TOAN_KHI_NHAN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatHang {
    private Long maDatHang;
    private HinhThucThanhToan hinhThucThanhToan = THANH_TOAN_KHI_NHAN; // vi du ve chuyen sang enum
    private int phuongThucVanChuyen;
    private TrangThaiDon tinhTrang;
    private NguoiDung nguoiDung;
    private String diaChiGiao;
    private LocalDateTime ngayTaoDon;
    private String tenNguoiNhan;
    private String sdtNhan;
    private String ghiChu;
    private List<SanPhamDat> danhSachSanPham;
    private double tongTien;

    public void capNhatTongTien() {
    	double res = 0;
    	for(SanPhamDat i : danhSachSanPham) {
    		res += i.getGia() * i.getSoLuong();
    	}
    	this.tongTien = res;
    }
}
