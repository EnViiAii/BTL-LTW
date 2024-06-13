package cf.laptrinhweb.btl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AnhSanPham {
    private Long maAnh;
    private SanPham sanPham;
    private String duongDan;
}
