package cf.laptrinhweb.btl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ThuongHieu {
    private Long maThuongHieu;
    private String tenThuongHieu;
}
