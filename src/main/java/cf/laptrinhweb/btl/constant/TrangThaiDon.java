package cf.laptrinhweb.btl.constant;

import lombok.Getter;

@Getter
public enum TrangThaiDon {
    CHO_DUYET ("Chờ duyệt", 1),
    DA_DUYET ("Đã duyệt", 2),
    DA_HUY ("Đã huỷ", 3),
    HOAN_TAT ("Hoàn tất", 4);

    private final String tieuDe;
    private final Integer giaTri;

    TrangThaiDon(String tieuDe, Integer giaTri) {
        this.tieuDe = tieuDe;
        this.giaTri = giaTri;
    }

    public static TrangThaiDon cuaGiaTri(int giaTri) {
        for (TrangThaiDon trangThaiDon : values()) {
            if (trangThaiDon.giaTri.equals(giaTri)) {
                return trangThaiDon;
            }
        }
        throw new RuntimeException("Gia tri cua trang thai don khong hop le");
    }
}
