package cf.laptrinhweb.btl.model;


import cf.laptrinhweb.btl.constant.SapXepDon;
import cf.laptrinhweb.btl.constant.TrangThaiDon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DieuKienDonHang extends PhanTrang {
    private List<TrangThaiDon> dsTrangThai = Arrays.asList(TrangThaiDon.values());
    private SapXepDon kieuSapXep = SapXepDon.MOI_NHAT;
}
