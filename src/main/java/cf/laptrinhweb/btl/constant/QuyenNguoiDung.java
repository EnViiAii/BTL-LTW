package cf.laptrinhweb.btl.constant;

import lombok.Getter;

@Getter
public enum QuyenNguoiDung {
    KHACH_HANG ("Khách hàng"),
    ADMIN ("Admin"),
    QUAN_LY ("Quản lý"),
    NHAN_VIEN ("Nhân viên");

    private final String nhan;

    QuyenNguoiDung(String nhan) {
        this.nhan = nhan;
    }
}
