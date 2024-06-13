package cf.laptrinhweb.btl.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DieuKienSanPham extends PhanTrang {
    private String tuKhoa;
    private Boolean daAn;
}
