package cf.laptrinhweb.btl.constant;

public enum HinhThucThanhToan {
    THE_NGAN_HANG (1),
    THANH_TOAN_KHI_NHAN (2);

    private final Integer giaTri;

    HinhThucThanhToan(Integer giaTri) {
        this.giaTri = giaTri;
    }

    public static HinhThucThanhToan cuaGiaTri(Integer giaTri) {
        for (HinhThucThanhToan hinhThucThanhToan : values()) {
            if (hinhThucThanhToan.giaTri.equals(giaTri)) {
                return hinhThucThanhToan;
            }
        }
        throw new RuntimeException("Gia tri cua hinh thuc thanh toan khong hop le");
    }
}
