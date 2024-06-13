package cf.laptrinhweb.btl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheLoai {
    private Long maTheLoai;
    private String tenTheLoai;
    private String anhDaiDien;
}
