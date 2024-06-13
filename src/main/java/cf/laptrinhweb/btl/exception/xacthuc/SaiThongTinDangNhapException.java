package cf.laptrinhweb.btl.exception.xacthuc;

import lombok.Getter;

@Getter
public class SaiThongTinDangNhapException extends RuntimeException {
    private final Long maNguoiDung;

    public SaiThongTinDangNhapException(Long maNguoiDung) {
        super("Tên đăng nhập hoặc mật khẩu không đúng");
        this.maNguoiDung = maNguoiDung;
    }

    public SaiThongTinDangNhapException() {
        this(null);
    }
}
