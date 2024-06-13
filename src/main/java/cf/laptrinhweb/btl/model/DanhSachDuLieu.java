package cf.laptrinhweb.btl.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DanhSachDuLieu {
    private String tenDanhSach;
    private List<BanGhiDuLieu> danhSachDuLieu;
}
