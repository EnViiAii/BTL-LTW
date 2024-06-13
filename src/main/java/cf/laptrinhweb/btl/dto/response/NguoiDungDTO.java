package cf.laptrinhweb.btl.dto.response;

import cf.laptrinhweb.btl.entity.NguoiDung;
import cf.laptrinhweb.btl.entity.Quyen;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NguoiDungDTO {
    private Long maNguoiDung;
    private String tenHienThi;
    private String tenDangNhap;
    private String email;
    private String soDienThoai;
    private boolean daKhoa;
    private List<String> danhSachQuyen;

    public NguoiDungDTO(NguoiDung nguoiDung) {
        this.maNguoiDung = nguoiDung.getMaNguoiDung();
        this.tenHienThi = nguoiDung.getTenHienThi();
        this.tenDangNhap = nguoiDung.getTenDangNhap();
        this.email = nguoiDung.getEmail();
        this.soDienThoai = nguoiDung.getSoDienThoai();
        this.daKhoa = nguoiDung.isDaKhoa();
        this.danhSachQuyen = nguoiDung.getDsQuyen().stream().map(Quyen::getTenQuyen).toList();
    }
}
