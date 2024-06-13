package cf.laptrinhweb.btl.constant;

import lombok.Getter;

@Getter
public enum SapXepDon {
    MOI_NHAT ("Mới nhất", "ngay_dat_hang", "DESC"),
    CU_NHAT ("Cũ nhất", "ngay_dat_hang", "ASC"),
    GIA_GIAM_DAN ("Giá trị giảm dần", "tong_tien", "DESC"),
    GIA_TANG_DAN ("Giá trị tăng dần", "tong_tien", "ASC");

    private final String tieuDe;
    private final String cot;
    private final String huongSapXep;

    SapXepDon(String tieuDe, String cot, String huongSapXep) {
        this.tieuDe = tieuDe;
        this.cot = cot;
        this.huongSapXep = huongSapXep;
    }
}
