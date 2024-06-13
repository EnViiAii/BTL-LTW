package cf.laptrinhweb.btl.exception.xacthuc;

import lombok.Getter;

@Getter
public class TaiKhoanBiKhoaException extends RuntimeException {
    private final Long maNguoiDung;

    public TaiKhoanBiKhoaException(Long maNguoiDung) {
        super("Tài khoản của bạn đã bị khoá. Liên hệ quản trị viên để biết thêm chi tiết");
        this.maNguoiDung = maNguoiDung;
    }
}
