package cf.laptrinhweb.btl.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TinTuc {
    private Integer ma;
    private String tieuDe;
    private String moTa;
    private String anh;
    private String nhanNutLienKet;
}
