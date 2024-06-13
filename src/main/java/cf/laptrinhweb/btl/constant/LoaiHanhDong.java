package cf.laptrinhweb.btl.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LoaiHanhDong {
    DANG_NHAP (1L),
    THAY_DOI_MAT_KHAU (2L),
    DANG_XUAT (3L),
    DOI_THONG_TIN_TAI_KHOAN (4L);

    private final Long giaTriNguyen;

    LoaiHanhDong(Long giaTriNguyen) {
        this.giaTriNguyen = giaTriNguyen;
    }

    public static LoaiHanhDong cuaGiaTri(Long giaTriNguyen) {
        return Arrays.stream(LoaiHanhDong.values())
            .filter(loaiHanhDong -> loaiHanhDong.giaTriNguyen.equals(giaTriNguyen))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }
}
