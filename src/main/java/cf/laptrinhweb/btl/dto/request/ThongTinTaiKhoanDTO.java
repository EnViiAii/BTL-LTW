package cf.laptrinhweb.btl.dto.request;

import cf.laptrinhweb.btl.entity.NguoiDung;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThongTinTaiKhoanDTO {
    private String tenHienThi;
    private String tenDangNhap;
    private String email;
    private String soDienThoai;
}
